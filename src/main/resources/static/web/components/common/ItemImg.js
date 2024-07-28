import Link from "next/link";
import React from "react";
import { Button } from "react-scroll/modules";
import MyLink from "./MyLink";

export default function ItemImg({ product }) {
  return (
    <div className="my-2 h-full md:my-0 md:mx-2 md:grid md:grid-cols-3">
      <div className="col-span-1">
        <MyLink href={`/hinh-anh/hinh-anh-1`}>
          <img
            src="https://images.unsplash.com/photo-1462396240927-52058a6a84ec?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=773&q=80"
            alt=""
            className=" h-full cursor-pointer object-cover"
          />
        </MyLink>
      </div>
      <div className="col-span-2 my-2 w-full text-black md:my-0 md:ml-1 md:p-2 ">
        <Link href="/hinh-anh/hinh-anh-1">
          <a className="font-semibold">Lorem ipsum dolor sit amet.</a>
        </Link>
        <p
          className="my-4 cursor-default overflow-hidden overflow-ellipsis"
          style={{
            display: '-webkit-box',
            '-webkit-line-clamp': '3',
            '-webkit-box-orient': 'vertical',
            overflow: 'hidden',
            textOverflow: 'ellipsis',
          }}
        >
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Sequi earum
          blanditiis eos minus placeat sint iure laborum esse possimus! Esse
          quae eaque eligendi magni porro deserunt vitae, est sint
          exercitationem!
        </p>
        <Link href="/hinh-anh/hinh-anh-1">
          <a className="bg-[#0060af] p-2 font-semibold text-white hover:bg-[#FACC15]">
            Xem thêm
          </a>
        </Link>
      </div>
    </div>
  );
}
