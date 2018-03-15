package nl.han.dea.marijn;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("asdf")
public class Helloworld {
    @GET
    @Consumes("text/plain")
    @Produces("text/plain")
    @Path("qwer")
    public String helloworld() {
        return "Helloworld!";
    }

    public String test(){
        return "test!";
    }
}