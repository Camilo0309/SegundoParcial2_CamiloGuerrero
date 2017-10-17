package com.jonmid.segundoparcial.Parser;

import com.jonmid.segundoparcial.Model.TeamModelCamiloGuerrero;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CAMILO on 17/10/2017.
 */

public class TeamJsonCamiloGuerrero {

    public static List<TeamModelCamiloGuerrero> getData(String content) throws JSONException {
    JSONObject jsonObject = new JSONObject(content);
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
    List<TeamModelCamiloGuerrero> teamlist = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
        JSONObject item = jsonArray.getJSONObject(i);
        TeamModelCamiloGuerrero modelCamiloGuerrero = new TeamModelCamiloGuerrero();
            modelCamiloGuerrero.setName(item.getString("name"));
            modelCamiloGuerrero.setCode(item.getString("code"));
        teamlist.add(modelCamiloGuerrero);
    }
        return teamlist;
}

}
