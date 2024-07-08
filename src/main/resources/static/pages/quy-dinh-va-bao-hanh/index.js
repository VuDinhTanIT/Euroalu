import React from "react";
import ItemImg from "../../components/common/ItemImg";
import Title from "../../components/common/Title";
import { MainLayout } from "../../components/layout/MainLayout";

export default function RegulationsNWarranties() {
  return (
    <MainLayout>
      <Title title={"QUY ĐỊNH & BẢO HÀNH"} />
      <section className="mt-10 mb-5 text-white md:mt-16">
        <div className="grid grid-cols-1 gap-0 md:grid-cols-2 md:gap-2">
          {[1, 2, 3, 4].map((item, index) => (
            <ItemImg key={index} />
          ))}
        </div>
      </section>
    </MainLayout>
  );
}
