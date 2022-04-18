package io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.util.media;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.github.kutaiba_nezar_kashmar.multiverse_of_geeks.domain.response.media.MediaProductionCompaniesResponse;
import io.github.kutaiba_nezar_kashmar.newapp.R;

public class MediaProductionCompanyAdapter extends
    RecyclerView.Adapter<MediaProductionCompanyAdapter.MovieProductionCompanyViewHolder>
{
  private ArrayList<MediaProductionCompaniesResponse> productionCompaniesResponses;

  public MediaProductionCompanyAdapter(
      ArrayList<MediaProductionCompaniesResponse> productionCompaniesResponses)
  {
    this.productionCompaniesResponses = productionCompaniesResponses;
  }

  @NonNull
  @Override
  public MovieProductionCompanyViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent, int viewType)
  {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater
        .inflate(R.layout.production_companies_item, parent, false);
    return new MovieProductionCompanyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MovieProductionCompanyViewHolder holder,
      int position)
  {
    holder.companyName
        .setText(productionCompaniesResponses.get(position).getName());
    holder.companyCountry.setText(
        productionCompaniesResponses.get(position).getOrigin_country());
    Glide.with(holder.context).load(
        "https://image.tmdb.org/t/p/w500" + productionCompaniesResponses
            .get(position).getLogo_path()).into(holder.companyLogo);
  }

  @Override
  public int getItemCount()
  {
    if (productionCompaniesResponses != null)
    {
      return productionCompaniesResponses.size();
    }
    return 0;
  }

  public void updateCompanyList(final ArrayList<MediaProductionCompaniesResponse> productionCompaniesResponses)
  {
    this.productionCompaniesResponses.clear();
    this.productionCompaniesResponses = productionCompaniesResponses;
    notifyDataSetChanged();
  }

  class MovieProductionCompanyViewHolder extends RecyclerView.ViewHolder
  {
    private final TextView companyName;
    private final TextView companyCountry;
    private final ImageView companyLogo;
    private Context context;

    public MovieProductionCompanyViewHolder(@NonNull View itemView)
    {
      super(itemView);
      context = itemView.getContext();
      companyName = itemView.findViewById(R.id.movie_company_name);
      companyCountry = itemView.findViewById(R.id.movie_company_country);
      companyLogo = itemView.findViewById(R.id.movie_company_logo);
    }
  }
}