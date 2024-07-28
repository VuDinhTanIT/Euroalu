import React from "react";
import Slider from "react-slick";
import { images } from "../../constants/image";
export default function VerticalMultiSlide() {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 3,
    autoplay: true,
    autoplaySpeed: 4000,
    pauseOnHover: true,
    responsive: [
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
        },
      },
    ],
  };
  return (
    <div className="mt-5 md:mt-20">
      <Slider {...settings}>
        {images?.map((each, index) => (
          <div key={index} className="flex h-[180px] w-full justify-center  ">
            <img
              src={each}
              alt={""}
              className="px-2 h-full w-full object-cover"
            />
          </div>
        ))}
      </Slider>
    </div>
  );
}
