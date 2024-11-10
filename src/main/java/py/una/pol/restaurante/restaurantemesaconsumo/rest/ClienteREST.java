/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package py.una.pol.restaurante.restaurantemesaconsumo.rest;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import py.una.pol.restaurante.restaurantemesaconsumo.ejb.ClienteDAO;
import py.una.pol.restaurante.restaurantemesaconsumo.entities.Cliente;

/**
 *
 * @author Verónica
 */
@Path("cliente")
@Consumes("application/json")
@Produces("application/json")

public class ClienteREST {
    @Inject
    private ClienteDAO clienteDAO;
    
    @GET
    @Path("/")
    public Response listar(){
        return Response.ok(clienteDAO.lista()).build();
    }
    
    @POST
    @Path("/")
    public Response crear(Cliente c){
        this.clienteDAO.agregar(c);
        return Response.ok().build();
    }
}
