import HorizontalMultiSlide from '../components/common/HorizontalMultiSlide';
import Items from '../components/common/Items';
import MyLink from '../components/common/MyLink';
import Ribbon from '../components/common/Ribbon';
import VerticalMultiSlide from '../components/common/VerticalMultiSlide';
import { MainLayout } from '../components/layout/MainLayout';
const data = [
  { name: 'cau-thang-kinh-1' },
  { name: 'cau-thang-kinh-1' },
  { name: 'cau-thang-kinh-1' },
  { name: 'cau-thang-kinh-1' },
  { name: 'cau-thang-kinh-1' },
  { name: 'cau-thang-kinh-1' },
  { name: 'cau-thang-kinh-1' },
  { name: 'cau-thang-kinh-1' },
];
export default function Home() {
  return (
    <MainLayout>
      <section className="my-10">
        <div className="grid grid-cols-1 gap-4 md:grid-cols-3">
          <div className="col-span-2 ">
            <div className="">
              <div className="border-l-8 border-l-gray-500  text-lg font-semibold md:text-2xl">
                <span className="px-6">GIỚI THIỆU DOANH NGHIỆP</span>
                <small className="hidden text-sm font-normal md:inline-block">
                  Chúng tôi cam kết mang lại sự hài lòng cho khách hàng
                </small>
              </div>
              <div className="my-6 ml-8">
                <h2 className=" my-2 text-justify font-semibold leading-8">
                  Công ty cổ phần xây dựng EuroAlu
                  <br />
                  Được thành lập vào tháng 11 năm 2022.
                </h2>

                <p className="my-2  text-justify">
                  EuroAlu được xây dựng bởi đội ngũ quản lý tận tụy, sáng tạo,
                  đội ngũ công nhân có tay nghề cao, và dây chuyền sản xuất được
                  ứng dụng công nghệ sản xuất tiên tiến từ Italy, Phần Lan, Hàn
                  Quốc ... Cùng với các nguyên tắc hoạt động cơ bản của công ty:
                  An toàn, Nhanh chóng, Áp dụng tiêu chuẩn chất lượng có uy tín
                  trên thế giới vào sản xuất ...
                </p>
              </div>
              <MyLink href="/gioi-thieu">
                <a className="align-center flex">
                  <svg
                    width="24"
                    height="24"
                    xmlns="http://www.w3.org/2000/svg"
                    fillRule="evenodd"
                    clipRule="evenodd"
                  >
                    <path d="M21.883 12l-7.527 6.235.644.765 9-7.521-9-7.479-.645.764 7.529 6.236h-21.884v1h21.883z" />
                  </svg>
                  <span className="ml-2 border-b-2 uppercase">Xem thêm</span>
                </a>
              </MyLink>
            </div>
            <VerticalMultiSlide />
          </div>

          <div className="hidden md:col-span-1 md:block ">
            <HorizontalMultiSlide />
          </div>
        </div>
      </section>
      <Ribbon title={'SẢN PHẨM NỔI BẬT'} />
      <section className=" ">
        <Items items={data} />
      </section>
      <Ribbon title={'SẢN PHẨM MỚI'} />
      <section className=" ">
        <Items items={data} />
      </section>
      <Ribbon title={'CÔNG TRÌNH TIÊU BIỂU'} />
      <section className=" ">
        <Items items={data} />
      </section>
    </MainLayout>
  );
}
