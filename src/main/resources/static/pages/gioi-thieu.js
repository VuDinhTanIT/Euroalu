import React from 'react';
import Title from '../components/common/Title';
import { MainLayout } from '../components/layout/MainLayout';
import { personnel } from '../constants/data';

export default function Intro() {
  return (
    <MainLayout>
      <Title title={'GIỚI THIỆU'} />
      <div className="text-lg">
        <p className=" font-semibold">
          Công ty cổ phần xây dựng EuroAlu được thành lập vào tháng 11 năm 2022
        </p>
        <p className="my-4">
          EuroAlu được xây dựng bởi đội ngũ quản lý tận tụy, sáng tạo, đội ngũ
          công nhân có tay nghề cao, và dây chuyền sản xuất được ứng dụng công
          nghệ sản xuất tiên tiến từ Italy, Phần Lan, Hàn Quốc ... Cùng với các
          nguyên tắc hoạt động cơ bản của công ty: An toàn, Nhanh chóng, Áp dụng
          tiêu chuẩn chất lượng có uy tín trên thế giới vào sản xuất ...
        </p>
        <p className="my-4">
          Nên sản phẩm của công ty chúng tôi đáp ứng được hầu hết nhu cầu thị
          hiếu của khách hàng trong và ngoài nước. Ngoài ra, hệ thống quản lý
          chất lượng của công ty chúng tôi phù hợp tiêu chuẩn ISO 9001:2008,
          chúng tôi tin tưởng rằng có thể cung cấp đến Quý khách hàng các sản
          phẩm có chất lượng tốt nhất và tối đa hóa sự thỏa mãn của Quý khách
          hàng.
        </p>
        <p>
          Sản phẩm chính của Công ty bao gồm: Mặt dựng kính - Cửa nhôm - Cửa
          nhựa lõi thép - Cửa cuốn.
        </p>
      </div>
      <div className="my-5 mx-auto w-full">
        <img
          src="https://images.unsplash.com/photo-1497366811353-6870744d04b2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1169&q=80"
          alt="hinh-anh-cong-ty"
          className="w-full object-cover"
        />
      </div>
      <div className="my-5 text-lg">
        <h3 className="mb-1 text-xl font-bold text-[#800000]">
          THÔNG TIN CHUNG:
        </h3>
        <ul className="ml-5 list-decimal">
          <li className="font-bold">NĂM THÀNH LẬP – NGÀNH NGHỀ KINH DOANH:</li>
          <ul className="my-2">
            <li>
              Chứng nhận ĐKKD số:
            </li>
            <li>Đại diện pháp luật: </li>
            <li>
              Ngành nghề kinh doanh chính: Mua bán, sản xuất, gia công, sửa chữa
              các sản phẩm ngành nhôm kính.
            </li>
          </ul>
          <li className="font-bold">NHÂN SỰ:</li>
          <ul className="my-2">
            <li>Quản lý</li>
            <li>Kỹ thuật viên:</li>
          </ul>
          <li className="font-bold">
            HỆ THỐNG QUẢN LÝ CHẤT LƯỢNG:
            <span className="font-normal">ISO 9001:2008</span>
          </li>

          <li className="font-bold">
            KÊ KHAI NĂNG LỰC VÀ KINH NGHIỆM NHÂN SỰ QUẢN LÝ:
          </li>
          {personnel.map((item, index) => (
            <div key={index} className="my-2">
              <div className="font-semibold underline">{item.role}</div>
              <div className="">
                <span className="font-semibold">Họ và tên:</span> {item.name}
              </div>
              <div className="">
                <span className="font-semibold">Bằng cấp:</span>
                {item.certificate}
              </div>
              <div className="">
                <span className="font-semibold">Kinh nghiệm:</span> {item.exp}
              </div>
            </div>
          ))}
        </ul>
      </div>
    </MainLayout>
  );
}
