package com.example.asher.anacexercize4.data;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MovieItemDTO implements Serializable {

    public static final String MOVIE_ITEM_BUNDLE_NAME = "movie";
    public static final String MOVIE_INDEX_BUNDLE_NAME = "index";

    private int _imageSmallResId;
    private int _imageBigResId;

    private String _movieTitle;
    private String _movieDescription;
    private String _movieWiki;
    private String _movieReleaseDate;
    private String _trailerIntentAddress;
    private String _posterUrl;

    public MovieItemDTO(int _imageSmallResId, int _imageBigResId, String _movieTitle,
                        String _movieDescription, String _movieWiki, String _movieReleaseDate,
                        String _movieTrailerId, String _posterUrl) {
        this._imageSmallResId = _imageSmallResId;
        this._imageBigResId = _imageBigResId;
        this._movieTitle = _movieTitle;
        this._movieDescription = _movieDescription;
        this._movieWiki = _movieWiki;
        this._movieReleaseDate = _movieReleaseDate;
        this._trailerIntentAddress = _movieTrailerId;
        this._posterUrl = _posterUrl;
    }

    public String get_trailerIntentAddress() {
        return _trailerIntentAddress;
    }

    public void set_trailerIntentAddress(String _trailerIntentAddress) {
        this._trailerIntentAddress = _trailerIntentAddress;
    }

    public int get_imageSmallResId() {
        return _imageSmallResId;
    }

    public void set_imageSmallResId(int _imageSmallResId) {
        this._imageSmallResId = _imageSmallResId;
    }

    public int get_imageBigResId() {
        return _imageBigResId;
    }

    public void set_imageBigResId(int _imageBigResId) {
        this._imageBigResId = _imageBigResId;
    }

    public String get_movieTitle() {
        return _movieTitle;
    }

    public void set_movieTitle(String _movieTitle) {
        this._movieTitle = _movieTitle;
    }

    public String get_movieDescription() {
        return _movieDescription;
    }

    public void set_movieDescription(String _movieDescription) {
        this._movieDescription = _movieDescription;
    }

    public String get_movieWiki() {
        return _movieWiki;
    }

    public void set_movieWiki(String _movieWiki) {
        this._movieWiki = _movieWiki;
    }

    public String get_movieReleaseDate() {
        return _movieReleaseDate;
    }

    public void set_movieReleaseDate(String _movieReleaseDate) {
        this._movieReleaseDate = _movieReleaseDate;
    }

    public String get_posterUrl() {
        return _posterUrl;
    }

    public void set_posterUrl(String _posterUrl) {
        this._posterUrl = _posterUrl;
    }

}
