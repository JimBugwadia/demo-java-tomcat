package com.nirmata.mservice;

import java.io.*;
import java.util.Map;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/locate")
public class LocateService {

    private static Logger _logger = LoggerFactory.getLogger(LocateService.class);

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response locateService(String data) throws IOException {

        _logger.debug("Got POST data: {}", data);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> jsonData = mapper.readValue(data, new TypeReference<Map<String, Object>>() {
        });

        _logger.debug("Got data: {}", jsonData);

        String name = (String) jsonData.get("name");
        int nameIndex = name.indexOf(".");
        if (nameIndex == -1) {
            nameIndex = name.length();
        }

        String service = name.substring(0, nameIndex);
        _logger.debug("Target service: {}", service);

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet("http://" + name + "/" + service + "/api/info");
        getRequest.addHeader("accept", "application/json");

        _logger.debug("Executing request {}", getRequest);

        long start = System.currentTimeMillis();
        HttpResponse response = httpClient.execute(getRequest);
        long end = System.currentTimeMillis();

        if (response.getStatusLine().getStatusCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                + response.getStatusLine().getStatusCode());
        }
        long time = (end-start);

        BufferedReader reader = new BufferedReader(
            new InputStreamReader((response.getEntity().getContent())));

        Map<String, Object> responseData = mapper.readValue(reader, new TypeReference<Map<String, Object>>() {
        });
        responseData.put("responseTime", time);

        String responseString = mapper.writeValueAsString(responseData);

        ResponseBuilder bldr = Response.status(Response.Status.OK);
        bldr.type(MediaType.APPLICATION_JSON);
        bldr.entity(responseString);
        return bldr.build();
    }
}
