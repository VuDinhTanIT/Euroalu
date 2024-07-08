import React from "react";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Slider from "react-slick";
import { images } from "../../constants/image";

const Slideshow = () => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    // speed: 2000,
    // autoplaySpeed: 2000,
    cssEase: "linear",
  };
  return (
    <div>
      <Slider {...settings}>
        {images?.map((each, index) => (
          <div key={index} className="flex h-[500px] w-full justify-center">
            <img
              src={each}
              alt={""}
              className="mx-auto h-full object-cover shadow-xl"
            />
          </div>
        ))}
      </Slider>
    </div>
  );
};

export default Slideshow;
