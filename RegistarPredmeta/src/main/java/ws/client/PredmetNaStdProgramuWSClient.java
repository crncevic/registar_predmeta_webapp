/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Petar
 */
public class PredmetNaStdProgramuWSClient extends RestWSClient {

    public PredmetNaStdProgramuWSClient(String path) {
        super(path);
    }

    public Response delete(int stdProgramId, int predmetId) throws ClientErrorException {

        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{stdProgramId}));
        webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{predmetId}));
        return webTarget.request().delete(Response.class);
    }

    public Response update_JSON(Object requestEntity) throws ClientErrorException {
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

}
