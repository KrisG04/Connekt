package com.hash.android.connekt;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Spandita Ghosh on 9/3/2017.
 */

public class HomeFragment extends Fragment {

    public HomeFragment() {

    }

    /**
     * Called to do initial creation of a fragment.  This is called after
     *  and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>Any restored child fragments will be created before the base
     * <code>Fragment.onCreate</code> method returns.</p>
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_home, container, false);

        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "opensans.ttf");

        TextView text1 = (TextView) rootView.findViewById(R.id.Text1);
        TextView text2 =(TextView) rootView.findViewById(R.id.Text2);
        TextView text3 = (TextView) rootView.findViewById(R.id.Image1Text);
        TextView text4 = (TextView) rootView.findViewById(R.id.Image2Text);

        text1.setTypeface(custom_font2, Typeface.BOLD);
        text2.setTypeface(custom_font2, Typeface.BOLD);
        text3.setTypeface(custom_font2, Typeface.BOLD);
        text4.setTypeface(custom_font2, Typeface.BOLD);
        return rootView;
    }
}
