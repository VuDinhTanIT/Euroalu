import React from "react";
import MyLink from "./MyLink";

export default function Product({ product }) {
  return (
    <div className="relative mx-2 h-full cursor-pointer transition-all hover:scale-105">
      <MyLink href={`/san-pham/${product.name}`}>
        <img
          src="https://images.unsplash.com/photo-1462396240927-52058a6a84ec?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=773&q=80"
          alt=""
          className="h-full w-full object-cover"
        />
      </MyLink>
      <div className="absolute bottom-0 w-full bg-gray-800 p-2 text-center text-white opacity-80">
        This is an Image
      </div>
    </div>
  );
}
