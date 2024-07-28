import React, { useState } from 'react';
import { Transition } from '@headlessui/react';
import { Link } from 'react-scroll';
import Image from 'next/image';
import Navbar from './Navbar';
import Images from '../../constants/image';
import call from '../../assets/icons/call.png';
import Slideshow from './Slideshow';
// import Logo from "../public/streamlineLogo.png";

export default function Header() {
  const [isOpen, setIsOpen] = useState(false);
  return (
    <>
      <header
        className="
        container 
      mx-auto 
      flex 
      items-center 
      justify-between bg-gradient-to-b from-white to-blue-50 py-2 px-4  md:flex md:items-center md:py-4"
      >
        <div className="flex items-center">Logo</div>
        <div
          className="
          hidden flex-col
          items-center 
        justify-center 
         md:flex md:flex-col md:items-center md:justify-end
        "
        >
          <div
            className="
            mb-2 
            font-black
          uppercase leading-8 
          text-[#FACC15] 
        md:text-3xl"
          >
            Công ty cổ phần xây dựng EuroAlu
          </div>
          <div>
            Địa chỉ: 55 Đường Võ An Ninh, Phường Hoà Xuân, Quận Cẩm Lệ, TP. Đà
            Nẵng
          </div>
          <div>Email: </div>
        </div>
        <div className="flex items-center ">
          <div className="mr-2 h-10 w-10">
            <Image src={call} alt="" className="h-full w-full object-cover" />
          </div>
          <div>
            <div>HOTLINE</div>
            <div className="text-xl font-bold text-[#FACC15]">0934160179</div>
          </div>
        </div>
      </header>
      <Navbar />
      <Slideshow />
    </>
  );
}
