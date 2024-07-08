import { useState } from 'react';
import MyLink from '../common/MyLink';
import { Transition } from '@headlessui/react';

export default function Navbar() {
  const [isOpen, setIsOpen] = useState(false);
  return (
    <div>
      <nav className="flex justify-between bg-[#0060af] text-white ">
        <ul className="flex font-semibold md:mx-auto md:justify-between">
          <li>
            <MyLink href="/">
              <a className="menu-item -ml-1 border-none md:ml-0 md:border-[#0060af]">
                Trang chủ
              </a>
            </MyLink>
          </li>
          <li className="mobile">
            <MyLink href="/gioi-thieu">
              <a href="" className="menu-item ">
                Giới thiệu
              </a>
            </MyLink>
          </li>
          <li className="mobile group relative">
            <MyLink href="/san-pham">
              <a
                href=""
                className="menu-item group-hover:border-[#FACC15]group-hover:text-[#FACC15]"
              >
                Sản phẩm
              </a>
            </MyLink>
            <ul className="invisible absolute left-0 z-50 mt-14 w-max bg-[#0060af] opacity-0 transition-all duration-500 group-hover:visible group-hover:mt-0 group-hover:opacity-100">
              <li className="sub-dropdown border-li relative">
                <a href="" className="menu-sub-item">
                  Cửa sổ
                </a>
                <ul className="sub-dropdown-content invisible absolute left-full top-full w-max bg-[#0060af] opacity-0 transition-all duration-500 ">
                  <li className="border-li">
                    <a href="" className="menu-sub-item">
                      Cửa sổ mở hất
                    </a>
                  </li>
                  <li className="border-li">
                    <a href="" className="menu-sub-item">
                      Cửa số mở quay
                    </a>
                  </li>
                  <li className="border-li">
                    <a href="" className="menu-sub-item">
                      Cửa số trượt
                    </a>
                  </li>
                </ul>
              </li>
              <li className=" border-li">
                <a href="" className="menu-sub-item">
                  Cửa đi
                </a>
              </li>
              <li className="sub-dropdown border-li relative">
                <a href="" className="menu-sub-item">
                  Sản phẩm kính
                </a>
                <ul className="sub-dropdown-content invisible absolute left-full top-full w-max bg-[#0060af] opacity-0 transition-all duration-500 ">
                  <li className="border-li">
                    <a href="" className="menu-sub-item">
                      Cầu thang kính
                    </a>
                  </li>
                  <li className="border-li">
                    <a href="" className="menu-sub-item">
                      Phòng tắm kính
                    </a>
                  </li>
                </ul>
              </li>
            </ul>
          </li>
          <li className="mobile">
            <MyLink href="/hinh-anh">
              <a href="" className=" menu-item ">
                Hình ảnh công ty
              </a>
            </MyLink>
          </li>
          <li className="mobile">
            <MyLink href="/quy-dinh-va-bao-hanh">
              <a href="" className=" menu-item ">
                Quy định và bảo hành
              </a>
            </MyLink>
          </li>
          <li className="mobile">
            <MyLink href="/tin-tuc">
              <a href="" className=" menu-item ">
                Tin tức
              </a>
            </MyLink>
          </li>
          <li className="mobile">
            <MyLink href="/tuyen-dung">
              <a href="" className=" menu-item ">
                Tuyển dụng
              </a>
            </MyLink>
          </li>
          <li className="mobile">
            <MyLink href="/lien-he">
              <a href="" className=" menu-item ">
                Liên hệ
              </a>
            </MyLink>
          </li>
        </ul>
        <div className="flex md:hidden">
          <button
            onClick={() => setIsOpen(!isOpen)}
            type="button"
            className="mr-2 inline-flex items-center justify-center  p-2 text-white outline-none focus:outline-none"
            aria-controls="mobile-menu"
            aria-expanded="false"
          >
            <span className="sr-only">Open main menu</span>
            {!isOpen ? (
              <svg
                className="block h-6 w-6"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
                aria-hidden="true"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M4 6h16M4 12h16M4 18h16"
                />
              </svg>
            ) : (
              <svg
                className="block h-6 w-6"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
                aria-hidden="true"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M6 18L18 6M6 6l12 12"
                />
              </svg>
            )}
          </button>
        </div>
      </nav>
      <Transition
        show={isOpen}
        enter="transition ease-out duration-100 transform"
        enterFrom="opacity-0 scale-95"
        enterTo="opacity-100 scale-100"
        leave="transition ease-in duration-75 transform"
        leaveFrom="opacity-100 scale-100"
        leaveTo="opacity-0 scale-95"
      >
        {(ref) => (
          <div className="md:hidden" id="mobile-menu">
            <div
              ref={ref}
              className="space-y-1 bg-[#0060af] px-2 pt-2 pb-3 text-white sm:px-3"
            >
              <ul className="flex flex-col font-semibold">
                <li>
                  <MyLink href="/gioi-thieu">
                    <a href="" className="menu-item-mobile ">
                      Giới thiệu
                    </a>
                  </MyLink>
                </li>
                <li>
                  <MyLink href="/san-pham">
                    <a href="" className="menu-item-mobile ">
                      Sản phẩm
                    </a>
                  </MyLink>
                </li>
                <li>
                  <MyLink href="/hinh-anh">
                    <a href="" className=" menu-item-mobile ">
                      Hình ảnh công ty
                    </a>
                  </MyLink>
                </li>
                <li>
                  <MyLink href="/quy-dinh-va-bao-hanh">
                    <a href="" className=" menu-item-mobile ">
                      Quy định và bảo hành
                    </a>
                  </MyLink>
                </li>
                <li>
                  <MyLink href="/tin-tuc">
                    <a href="" className=" menu-item-mobile ">
                      Tin tức
                    </a>
                  </MyLink>
                </li>
                <li>
                  <MyLink href="/tuyen-dung">
                    <a href="" className=" menu-item-mobile ">
                      Tuyển dụng
                    </a>
                  </MyLink>
                </li>
                <li>
                  <MyLink href="/lien-he">
                    <a href="" className=" menu-item-mobile ">
                      Liên hệ
                    </a>
                  </MyLink>
                </li>
              </ul>
            </div>
          </div>
        )}
      </Transition>
    </div>
  );
}
