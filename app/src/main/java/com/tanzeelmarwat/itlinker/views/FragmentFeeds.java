package com.tanzeelmarwat.itlinker.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.tanzeelmarwat.itlinker.R;
import com.tanzeelmarwat.itlinker.adapters.FeedsAdapter;
import com.tanzeelmarwat.itlinker.listeners.OnListItemClickListener;
import com.tanzeelmarwat.itlinker.models.Feed;
import com.tanzeelmarwat.itlinker.models.User;
import com.tanzeelmarwat.itlinker.network.API;
import com.tanzeelmarwat.itlinker.utils.Constants;
import com.tanzeelmarwat.itlinker.utils.Utility;
import com.tanzeelmarwat.itlinker.volley.NetworkRequest;
import com.tanzeelmarwat.itlinker.volley.NetworkResponseListener;
import com.tanzeelmarwat.itlinker.volley.NetworkResponseParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentFeeds.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentFeeds#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFeeds extends Fragment implements OnListItemClickListener {
    private String TAG = "FragmentFeeds";
    private static final String USER_TYPE = "user_type";

    private String userType;

    private Context mContext;
    private RecyclerView rvFeeds;
    private RecyclerView.LayoutManager mLayoutManager;
    private FeedsAdapter myJobsActiveAdapter;
    private HashMap<Integer, Feed> feeds;

    private OnFragmentInteractionListener mListener;

    public FragmentFeeds() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userType Parameter 1.
     * @return A new instance of fragment FragmentFeeds.
     */
    public static FragmentFeeds newInstance(String userType) {
        FragmentFeeds fragment = new FragmentFeeds();
        Bundle args = new Bundle();
        args.putString(USER_TYPE, userType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userType = getArguments().getString(USER_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feeds, container, false);
        mContext = getContext();
        rvFeeds = view.findViewById(R.id.rvFeeds);
        rvFeeds.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        rvFeeds.setLayoutManager(mLayoutManager);

        if(userType != null && !userType.equalsIgnoreCase("")) {
            String userTypeId = Utility.getUserTypeId(User.getUserTypes(),userType);
            getFeeds(userTypeId);
        }


        return view;
    }

    private void getFeeds(String userType) {
        if (Utility.isConnectedToInternet(mContext)) {
            NetworkRequest.getInstance().getFeeds(getFeedsListener, userType);
        } else {
            Toast.makeText(mContext, getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
        }
    }

    public NetworkResponseListener getFeedsListener = new NetworkResponseListener() {
        @Override
        public void onSuccess(String response) {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray userdata = obj.getJSONArray(API.DATA_RESULT);
                feeds = NetworkResponseParser.getInstance().getFeedsParser(userdata);
                myJobsActiveAdapter = new FeedsAdapter(mContext, feeds, FragmentFeeds.this);
                rvFeeds.setAdapter(myJobsActiveAdapter);

            } catch (JSONException e) {
                Log.e(Constants.TAG, TAG + " JSONException : " + e.getMessage());
            }
        }

        @Override
        public void onVolleyError(VolleyError error) {
            Log.e(Constants.TAG, TAG + " Volley Error : " + error.getMessage());
        }

        @Override
        public void onServerError(String error) {
            Log.e(Constants.TAG, TAG + " Server Error : " + error);
        }
    };

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListItemClick(int position) {

    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.tvLike :
                if(view.isSelected()) {
                    view.setSelected(false);
                } else {
                    view.setSelected(true);
                }
                break;

            case R.id.tvComment :
                if(view.isSelected()) {
                    view.setSelected(false);
                } else {
                    view.setSelected(true);
                }
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
