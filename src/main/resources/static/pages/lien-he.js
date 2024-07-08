import React from 'react';
import Title from '../components/common/Title';
import { MainLayout } from '../components/layout/MainLayout';

export default function Contact() {
  return (
    <MainLayout>
      <Title title={'LIÊN HỆ'} />
      <section className="flex flex-col items-center md:flex-row md:space-x-6">
        <div className="w-full md:w-1/2">
          <div className="mb-4 space-y-2">
            <div className="text-[17px] font-bold text-primary md:text-2xl">
              Công ty cổ phần EuroAlu
            </div>
            <div>
              Địa chỉ: 55 Đường Võ An Ninh, Phường Hoà Xuân, Quận Cẩm Lệ, TP. Đà
              Nẵng
            </div>

            <div>Email: hungle@gmail.com</div>
            <div>Điện thoại: 0934160179</div>
          </div>
          <div className="mt-2">
            <form action="#" method="POST">
              <div className="overflow-hidden shadow">
                <div className="space-y-1 bg-white px-4 py-5">
                  <div className="grid grid-rows-1 md:grid-cols-12 ">
                    <div className="col-span-2 flex items-center">
                      <label className="block text-sm font-medium text-gray-700">
                        Họ và tên: <span className="text-[#ff0000]">*</span>
                      </label>
                    </div>
                    <div className="col-span-10">
                      <input
                        type="text"
                        name="first_name"
                        id="first_name"
                        className="mt-1 block w-full rounded-md border border-gray-300 p-1.5 shadow-sm  "
                      />
                    </div>
                  </div>

                  <div className="grid grid-rows-1 md:grid-cols-12 ">
                    <div className="col-span-2 flex items-center">
                      <label className="block text-sm font-medium text-gray-700">
                        Địa chỉ:<span className="text-[#ff0000]">*</span>
                      </label>
                    </div>
                    <div className="col-span-10">
                      <input
                        type="text"
                        name="first_name"
                        id="first_name"
                        className="mt-1 block w-full rounded-md border border-gray-300 p-1.5 shadow-sm  "
                      />
                    </div>
                  </div>

                  <div className="grid grid-rows-1 md:grid-cols-12 ">
                    <div className="col-span-2 flex items-center">
                      <label className="block text-sm font-medium text-gray-700">
                        Điện thoại:<span className="text-[#ff0000]">*</span>
                      </label>
                    </div>
                    <div className="col-span-10">
                      <input
                        type="text"
                        name="first_name"
                        id="first_name"
                        className="mt-1 block w-full rounded-md border border-gray-300 p-1.5 shadow-sm  "
                      />
                    </div>
                  </div>
                  <div className="grid  grid-rows-1 md:grid-cols-12">
                    <div className="col-span-2 flex items-center">
                      <label className="block text-sm font-medium text-gray-700">
                        Email:<span className="text-[#ff0000]">*</span>
                      </label>
                    </div>
                    <div className="col-span-10">
                      <input
                        type="email"
                        name="email_address"
                        id="email_address"
                        className="mt-1 block w-full rounded-md border border-gray-300 p-1.5 shadow-sm  "
                      />
                    </div>
                  </div>
                  <div className="grid  grid-rows-1 md:grid-cols-12">
                    <div className="col-span-2 flex items-center">
                      <label className="block text-sm font-medium text-gray-700">
                        Chủ đề:<span className="text-[#ff0000]">*</span>
                      </label>
                    </div>
                    <div className="col-span-10">
                      <input
                        type="text"
                        name="first_name"
                        id="first_name"
                        className="mt-1 block w-full rounded-md border border-gray-300 p-1.5 shadow-sm  "
                      />
                    </div>
                  </div>
                  <div className="grid grid-rows-1 pt-2 md:grid-cols-12">
                    <div className="col-span-2 ">
                      <label className="block text-sm font-medium text-gray-700">
                        Nội dung:<span className="text-[#ff0000]">*</span>
                      </label>
                    </div>
                    <div className="col-span-10">
                      <textarea
                        id="message"
                        rows="4"
                        className=" mt-1 block w-full rounded-lg border border-gray-300 p-2.5 text-sm text-gray-900 shadow-sm focus:border-blue-500 focus:ring-blue-500  dark:focus:border-blue-500 dark:focus:ring-blue-500"
                        placeholder=""
                      ></textarea>
                    </div>
                  </div>
                </div>
                <div className="bg-gray-50 px-4 py-3 ">
                  <button
                    type="submit"
                    className="hover:c80011 inline-flex justify-center rounded-md border border-transparent bg-primary py-2 px-4 text-sm font-medium text-white shadow-sm focus:outline-none "
                  >
                    Gửi
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
        <div className="mt-8 w-full md:mt-0 md:w-1/2">
          <div className="w-full">
            <iframe
              width="100%"
              height="500"
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
      </section>
    </MainLayout>
  );
}
