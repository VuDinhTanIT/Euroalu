import React from "react";
export default function Ribbon({ title }) {
  return (
    <section className="mt-10 flex justify-center overflow-hidden text-white md:mt-16">
      <div className="ribbon flex justify-center">
        <small className=" mx-auto text-sm font-semibold  uppercase md:text-xl">
          {title}
        </small>
      </div>
    </section>
  );
}
