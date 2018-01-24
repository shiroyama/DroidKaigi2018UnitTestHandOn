package us.shiroyama.android.my_repositories.presentation.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.threeten.bp.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import us.shiroyama.android.my_repositories.R;
import us.shiroyama.android.my_repositories.presentation.viewmodel.RepoViewModel;

/**
 * Adapter for Repository List
 *
 * @author Fumihiko Shiroyama
 */

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ViewHolder> {
  @NonNull
  private final LayoutInflater layoutInflater;

  @NonNull
  private final Context context;

  private final List<RepoViewModel> viewModels = new ArrayList<>();

  public RepoListAdapter(@NonNull Context context) {
    layoutInflater = LayoutInflater.from(context);
    this.context = context.getApplicationContext();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = layoutInflater.inflate(R.layout.repo_list_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    RepoViewModel viewModel = viewModels.get(position);
    holder.textViewRepoName.setText(viewModel.getRepoName());
    holder.textViewDescription.setText(viewModel.getDescription());
    holder.textViewUpdatedAt.setText(viewModel.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    Picasso.with(context).load(viewModel.getOwnerAvatarUrl()).into(holder.imageViewAuthorIcon);
  }

  @Override
  public int getItemCount() {
    return viewModels.size();
  }

  public void setViewModels(@NonNull List<RepoViewModel> viewModels) {
    synchronized (this.viewModels) {
      this.viewModels.clear();
      this.viewModels.addAll(viewModels);
    }
    notifyDataSetChanged();
  }

  /**
   * View Holder
   */
  static class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.repo_name)
    TextView textViewRepoName;

    @BindView(R.id.description)
    TextView textViewDescription;

    @BindView(R.id.updated_at)
    TextView textViewUpdatedAt;

    @BindView(R.id.author_icon)
    ImageView imageViewAuthorIcon;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
