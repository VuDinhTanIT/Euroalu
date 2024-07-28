import Image from 'next/image';
import React from 'react';
import { FaInstagram } from 'react-icons/fa';
import { FaTwitter } from 'react-icons/fa';
import { FaLinkedin } from 'react-icons/fa';
import { FaYoutube } from 'react-icons/fa';
import zalo from '../../assets/icons/zalo.png';

export default function Footer() {
  return (
    <footer className="mt-10 md:mt-20">
      <div className=" flex h-1/2  flex-col items-start justify-between bg-black p-6 text-white  md:h-1/4 md:p-24 lg:flex-row">
        <div className="w-full md:w-1/2">
          <div
            className="
            mb-2
           flex
          flex-col
        items-start 
        justify-center
        "
          >
            <div
              className="
              mb-2
              flex
              flex-col
            text-base 
            font-black
          uppercase leading-8 
        md:text-xl"
            >
              Công ty cổ phần xây dựng EuroAlu
            </div>
            <div>
              Địa chỉ: 55 Đường Võ An Ninh, Phường Hoà Xuân, Quận Cẩm Lệ, TP. Đà
              Nẵng
            </div>
            <div>Email: </div>
          </div>
          <div className="flex">
            <div className="mr-4 text-sm uppercase md:text-lg">
              Mạng xã hội:
            </div>
            <div className="flex items-center gap-6 pb-5">
              <FaInstagram className="cursor-pointer text-2xl hover:text-yellow-600" />
              <FaTwitter className="cursor-pointer text-2xl hover:text-blue-600" />
              <FaLinkedin className="cursor-pointer text-2xl hover:text-blue-600" />
              <FaYoutube className="cursor-pointer text-2xl hover:text-red-600" />
              <Image src={zalo} alt="" className="h-full w-full cursor-pointer" />
            </div>
          </div>
        </div>

        <div className=" w-full md:w-1/2">
          <div className="mb-2 text-sm uppercase md:text-lg">Bản đồ:</div>

          <div className="w-full">
            <div className="mapouter">
              <div className="gmap_canvas">
                <iframe
                  width="100%"
                  height="300"
                  id="gmap_canvas"
                  src="https://maps.google.com/maps?q=55%20%C4%90%C6%B0%E1%BB%9Dng%20V%C3%B5%20An%20Ninh,%20Ph%C6%B0%E1%BB%9Dng%20Ho%C3%A0%20Xu%C3%A2n,%20Qu%E1%BA%ADn%20C%E1%BA%A9m%20L%E1%BB%87,%20TP.%20%C4%90%C3%A0%20N%E1%BA%B5ng&t=&z=13&ie=UTF8&iwloc=&output=embed"
                  frameBorder="0"
                  scrolling="no"
                  marginHeight="0"
                  marginWidth="0"
                ></iframe>
                <a href="https://www.whatismyip-address.com/divi-discount/"></a>
              </div>
            </div>
            {/* <iframe
              width="100%"
              height="300"
              frameBorder="0"
              scrolling="no"
              marginHeight="0"
              marginWidth="0"
              src="https://maps.google.com/maps?width=100%25&amp;height=300&amp;hl=en&amp;q=27,%20Nguy%E1%BB%85n%20B%E1%BA%A3o,%20H%C3%B2a%20Ch%C3%A2u,%20H%C3%B2a%20Vang,%20%C4%90%C3%A0%20N%E1%BA%B5ng+(H%C3%B9ng%20Phong)&amp;t=&amp;z=14&amp;ie=UTF8&amp;iwloc=B&amp;output=embed"
            >
              <a href="https://www.gps.ie/marine-gps/">navigation gps</a>
            </iframe> */}
          </div>
        </div>
      </div>
      <div className="flex flex-col items-center justify-center bg-[#0060af]  p-5 text-center text-sm text-white md:text-lg">
        <h1 className=" font-semibold ">
          © 2021-2022 by 7huh7. All rights reserved
        </h1>
      </div>
    </footer>
  );
}
