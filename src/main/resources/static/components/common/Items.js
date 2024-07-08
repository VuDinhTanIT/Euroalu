import React from "react";
import Slider from "react-slick";
import { images } from "../../constants/image";
import Product from "./Product";
export default function Items({ items }) {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1,
    initialSlide: 0,
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
    <div className="mt-5 md:mt-10">
      <Slider {...settings}>
        {items.map((product, index) => (
          <Product key={index} product={product} />
        ))}
      </Slider>
    </div>
  );
}
