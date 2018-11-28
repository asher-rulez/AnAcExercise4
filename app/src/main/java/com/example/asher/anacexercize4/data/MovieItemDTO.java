package com.example.asher.anacexercize4.data;


public class MovieItemDTO {

    private int _imageSmallResId;
    private int _imageBigResId;

    private String _movieTitle;
    private String _movieDescription;
    private String _movieWiki;
    private String _movieReleaseDate;

    public MovieItemDTO(int _imageSmallResId, int _imageBigResId, String _movieTitle, String _movieDescription, String _movieWiki, String _movieReleaseDate) {
        this._imageSmallResId = _imageSmallResId;
        this._imageBigResId = _imageBigResId;
        this._movieTitle = _movieTitle;
        this._movieDescription = _movieDescription;
        this._movieWiki = _movieWiki;
        this._movieReleaseDate = _movieReleaseDate;
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
}
