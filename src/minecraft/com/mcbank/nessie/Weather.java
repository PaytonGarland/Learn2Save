package com.mcbank.nessie;

public class Weather
{
    private Response response;
    private transient Current_Observation current_observation;

    public Weather()
    {
        response = new Response();
        current_observation = new Current_Observation();
    }

    public Current_Observation getCurrObs()
    {
        return this.current_observation;
    }
}
