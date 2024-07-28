import React from "react";
import Slider from "react-slick";
import { images } from "../../constants/image";
export default function HorizontalMultiSlide() {
  const settings = {
    dots: true,
    infinite: true,
    slidesToShow: 2,
    slidesToScroll: 1,
    vertical: true,
    speed: 500,
    autoplay: true,
    autoplaySpeed: 4000,
    pauseOnHover: true,
    arrows: false,
  };
  return (
    <div>
      <Slider {...settings}>
        {images?.map((each, index) => (
          <div key={index} className="flex h-[253px] w-full ">
            <img src={each} alt={""} className="mx-auto h-full object-cover" />
          </div>
        ))}
      </Slider>
    </div>
  );
}
