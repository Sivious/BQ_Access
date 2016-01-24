package bq.dropbox.library.ui.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import bq.dropbox.library.R;
import bq.dropbox.library.model.EpubDetailedInfoItem;
import bq.dropbox.library.ui.main.MainActivity;
import bq.dropbox.library.ui.main.fragments.presenters.EpubInfoPresenter;
import bq.dropbox.library.ui.main.fragments.presenters.EpubInfoPresenterImpl;

/**
 * Created by Javier on 24/01/2016.
 */
public class EpubInfoFragmentImpl extends Fragment implements EpubInfoFragment {
    private String path;
    private View view;
    private ImageView close;
    private EpubInfoPresenter presenter;
    private ImageView cover;
    private TextView title;

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void setContent(EpubDetailedInfoItem item) {
        cover.setImageBitmap(item.getCoverImage());
        title.setText(item.getTitle());
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.epub_detail_fragment, null);
        setView();

        return view;
    }

    private void setView() {
        presenter = new EpubInfoPresenterImpl(this.getContext(), this, path);
        presenter.getFileFromDropbox();

        close = (ImageView) view.findViewById(R.id.epub_detail_close);
        cover = (ImageView) view.findViewById(R.id.epub_detail_cover);
        title = (TextView) view.findViewById(R.id.epub_detail_title);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).hideEpubInfo();
            }
        });
    }


}
