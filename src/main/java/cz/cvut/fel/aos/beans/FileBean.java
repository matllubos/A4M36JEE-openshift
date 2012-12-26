package cz.cvut.fel.aos.beans;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.activation.DataHandler;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.Serializable;

/** @author Karel Cemus */
@Slf4j
@Getter
@Named( "file" )
@SessionScoped
public class FileBean implements Serializable {


    private String title;

    private String name;

    private int length;

    private byte[] content;

    public void setFile( String fileTitle, String fileName, byte[] content ) {
        this.title = fileTitle;
        this.name = fileName;
        this.length = content.length;
        this.content = content;
    }

    public void setFile( String fileTitle, String fileName, DataHandler handler ) {
        this.title = fileTitle;
        this.name = fileName;

        content = new byte[ 1024 * 100 ];
        try {
            length = handler.getInputStream().read( content );
        } catch ( IOException e ) {
            FileBean.log.warn( "Stažení souboru se nepovedlo.", e );
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Příprava souboru se nezdařila, opakujte prosím akci později." ) );
        }
    }

    public void download() {
        download( name, length, content );
    }

    public void download( String name, DataHandler handler ) {
        try {
            byte[] content = new byte[ 1024 * 100 ];
            int length = handler.getInputStream().read( content );
            download( name, length, content );
        } catch ( IOException e ) {
            FileBean.log.warn( "Stažení souboru se nepovedlo.", e );
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Příprava souboru se nezdařila, opakujte prosím akci později." ) );
        }
    }

    public void download( String name, int length, byte[] content ) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = ( HttpServletResponse ) externalContext.getResponse();

        // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        response.reset();
        // Check http://www.w3schools.com/media/media_mimeref.asp for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
        response.setContentType( "plain/text; charset=utf-8" );
        // The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE, it will use current request URL as filename instead.
        response.setHeader( "Content-disposition", "attachment; filename=\"" + name + "\"" );

        BufferedOutputStream output = null;

        try {
            output = new BufferedOutputStream( response.getOutputStream() );

            byte[] buffer = new byte[ 10240 ];
            output.write( content, 0, length );
        } catch ( IOException e ) {
            FileBean.log.warn( "File transfer failed.", e );
        } finally {
            if ( output != null ) {
                try {
                    output.close();
                } catch ( IOException ignored ) {
                    //  ignore
                }
            }
        }

        // Important! Else JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
        facesContext.responseComplete();
    }

    public boolean isStored() {
        return content != null;
    }

    public void reset() {
        // prevent memory leak
        content = null;
        title = null;
        name = null;
        length = 0;
    }
}
