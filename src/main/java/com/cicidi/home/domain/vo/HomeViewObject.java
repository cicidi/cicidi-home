package com.cicidi.home.domain.vo;

/**
 * Created by cicidi on 2/16/2017.
 */
public class HomeViewObject {
    //    private String link = "https://az616578.vo.msecnd.net/files/responsive/cover/main/desktop/2016/07/31/636055810445094652-1915814807_Dogs3.jpg";
//    private String link_1 = "https://lh6.googleusercontent.com/ZuYV5ZKOyWj8llB0iCZqoyCAUH7r6ye8XFigWSdzvivQnLMsEZOXGRzGN42nDzpYNv0qrSonlpppMmo=w1920-h984-rw";
//    private String link_2 = "https://lh4.googleusercontent.com/vYrWGI0Bay1MQAKeXQvAAeb-l-dvmmPzLwjKmDlfV1HP_ywr_zUzV1p0acosLAPGLqZf58TJbmU9Z7Q=w1920-h984-rw";
//    private String link_3 = "https://lh6.googleusercontent.com/eUFkzmNbuIfVkBJm22mq0q5i_DKvYk4VGqrIvPRrB9gKaSpqZd9UyvMwN18jfp0y-Ci6V0GdiemrMz4=w1920-h984-rw";
    private OwlCarousel owlCarousel;
    private AboutMe aboutMe;
    private Feature feature;
    private Objective objective;

    public OwlCarousel getOwlCarousel() {
        return owlCarousel;
    }

    public void setOwlCarousel(OwlCarousel owlCarousel) {
        this.owlCarousel = owlCarousel;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Objective getObjective() {
        return objective;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

}
