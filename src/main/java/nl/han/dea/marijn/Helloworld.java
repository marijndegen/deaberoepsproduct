package nl.han.dea.marijn;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

// It's good practice to include a version number in the path so you can have
// multiple versions deployed at once. That way consumers don't need to upgrade
// right away if things are working for them.
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