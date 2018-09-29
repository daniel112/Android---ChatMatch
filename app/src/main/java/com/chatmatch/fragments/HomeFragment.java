package com.chatmatch.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chatmatch.R;
import com.chatmatch.activities.VideoActivity;
import com.chatmatch.activities.WebViewActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    ImageView imageViewPlay;
    Button buttonLogIn;
    Button buttonSignIn;
    RelativeLayout rl_ContentWrapper;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.fragment_home, container, false);
        imageViewPlay = RootView.findViewById(R.id.imageViewPlay);
        rl_ContentWrapper = RootView.findViewById(R.id.rlContentWrapper);

        // Handle Image Play Button
        imageViewPlay.setImageResource(R.drawable.imagevideo);
        imageViewPlay.setOnClickListener(this);

        // Handle Buttons
        buttonLogIn = RootView.findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(this);
        buttonSignIn = RootView.findViewById(R.id.buttonSignUp);
        buttonSignIn.setOnClickListener(this);

        return RootView;
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
    public void onClick(View v) {
//        Bundle bundle = new Bundle();
//        bundle.putBoolean("isLogIn", true);
//        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//        WebViewFragment webViewFragment;
        Intent intent;

        switch (v.getId()) {
            case R.id.buttonLogIn:
//                webViewFragment = new WebViewFragment();
//                bundle.putBoolean("isLogIn", true);
//                webViewFragment.setArguments(bundle);
//                transaction.replace(R.id.frameLayout, webViewFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("isLogIn", true);
                startActivity(intent);
                break;
            case R.id.buttonSignUp:
//                webViewFragment = new WebViewFragment();
//                bundle.putBoolean("isLogIn", true);
//                webViewFragment.setArguments(bundle);
//                transaction.replace(R.id.frameLayout, webViewFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("isLogIn", true);
                startActivity(intent);
                break;
            case R.id.imageViewPlay:
                intent = new Intent(getActivity(), VideoActivity.class);
                startActivity(intent);
            default:
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
