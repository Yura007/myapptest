package info.yuri.msapps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Yuri on 30/12/2017.
 */
public class Movie implements Parcelable,Comparable   {

    private static final String TAG = "Movie";


    @Expose(serialize = false, deserialize = false)
    int _id;
    @SerializedName("title")
    String titlel;
    @SerializedName("image")
    String image;
    @SerializedName("rating")
    String rating;
    @SerializedName("releaseYear")
    Integer releaseYear;

    @SerializedName("genre")
    List<String> genre;



    @Expose(serialize = false, deserialize = false)
    private String qr;

    public Movie(int _id, String titlel, String image, String rating, Integer releaseYear, List<String> genre,String QR) {
        this._id = _id;
        this.titlel = titlel;
        this.image = image;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.qr = QR;
    }

    public int get_id() {
        return _id;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }
    public String getTitlel() {
        return titlel;
    }

    public void setTitlel(String titlel) {
        this.titlel = titlel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getGenre() {
        return genre;
    }

    public String getGenreString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i =0 ;i <genre.size();i++){
            stringBuilder.append(genre.get(i));
            if (i<genre.size()-1)
            stringBuilder.append(" , ");
        }

        return stringBuilder.toString();
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._id);
        dest.writeString(this.titlel);
        dest.writeString(this.image);
        dest.writeString(this.rating);
        dest.writeString(this.qr);
        dest.writeValue(this.releaseYear);
        dest.writeStringList(this.genre);
    }

    protected Movie(Parcel in) {
        this._id = in.readInt();
        this.titlel = in.readString();
        this.image = in.readString();
        this.rating = in.readString();
        this.qr = in.readString();
        this.releaseYear = (Integer) in.readValue(Integer.class.getClassLoader());
        this.genre = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int compareTo(@NonNull Object o) {

        int compareYear=((Movie)o).getReleaseYear();
         /* For Ascending order*/
//        return  this.releaseYear-compareYear;
         /* For Descending order */
        return   compareYear-this.releaseYear;
    }
}