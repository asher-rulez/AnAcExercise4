package com.example.asher.anacexercize4.data;

import android.content.Context;

import com.example.asher.anacexercize4.R;

import java.util.ArrayList;

public class DataGetter {
    public static ArrayList<MovieItemDTO> GetData(Context context){
        ArrayList<MovieItemDTO> list = new ArrayList<MovieItemDTO>();

        list.add(new MovieItemDTO(R.drawable.infinity_war_poster_small, R.drawable.infinity_war_poster_big,
                context.getString(R.string.infinity_title), context.getString(R.string.infinity_description), context.getString(R.string.infinity_wiki),
                context.getString(R.string.infinity_rd), context.getString(R.string.infinity_trailer)));
        list.add(new MovieItemDTO(R.drawable.black_panther_small, R.drawable.black_panther_big,
                context.getString(R.string.black_panther_title), context.getString(R.string.black_panther_description), context.getString(R.string.black_panter_wiki),
                context.getString(R.string.black_panter_rd), context.getString(R.string.black_panter_trailer)));
        list.add(new MovieItemDTO(R.drawable.deadpool_two_small, R.drawable.deadpool_big,
                context.getString(R.string.deadpool_title), context.getString(R.string.deadpool_description), context.getString(R.string.deadpool_wiki),
                context.getString(R.string.deadpool_rd), context.getString(R.string.deadpool_trailer)));
        list.add(new MovieItemDTO(R.drawable.first_purge_small, R.drawable.purge_big,
                context.getString(R.string.first_purge_title), context.getString(R.string.first_purge_description), context.getString(R.string.purge_wiki),
                context.getString(R.string.purge_rd), context.getString(R.string.purge_trailer)));
        list.add(new MovieItemDTO(R.drawable.guardians_galaxy_small, R.drawable.guardians_big,
                context.getString(R.string.guardians_title), context.getString(R.string.guardians_descrtioption), context.getString(R.string.guardians_wiki),
                context.getString(R.string.guardians_rd), context.getString(R.string.guardians_trailer)));
        list.add(new MovieItemDTO(R.drawable.insterstellar_small, R.drawable.interstellar_big,
                context.getString(R.string.interstellar_title), context.getString(R.string.interstellar_description), context.getString(R.string.interstellar_wiki),
                context.getString(R.string.interstellar_rd), context.getString(R.string.interstellar_trailer)));
        list.add(new MovieItemDTO(R.drawable.jurasic_world_small, R.drawable.jurassic_big,
                context.getString(R.string.jurasic_title), context.getString(R.string.jurasic_description), context.getString(R.string.jurassic_wiki),
                context.getString(R.string.jurassic_rd), context.getString(R.string.jurassic_trailer)));
        list.add(new MovieItemDTO(R.drawable.meg_small, R.drawable.meg_big,
                context.getString(R.string.meg_title), context.getString(R.string.meg_description), context.getString(R.string.meg_wiki),
                context.getString(R.string.meg_rd), context.getString(R.string.meg_trailer)));
        list.add(new MovieItemDTO(R.drawable.oceans_eight_small, R.drawable.oceans_big,
                context.getString(R.string.oceans_title), context.getString(R.string.oceans_description), context.getString(R.string.oceans_wiki),
                context.getString(R.string.oceans_rd), context.getString(R.string.oceans_trailer)));
        list.add(new MovieItemDTO(R.drawable.thor_ragnarok_small, R.drawable.thor_big,
                context.getString(R.string.thor_title), context.getString(R.string.thor_description), context.getString(R.string.thor_wiki),
                context.getString(R.string.thor_rd), context.getString(R.string.thor_trailer)));

        return list;
    }
}
