import { collection, getDocs } from "firebase/firestore";
import React, { useEffect, useState } from "react";
import Title from "../../components/common/Title";
import { MainLayout } from "../../components/layout/MainLayout";
import { database } from "../../firebaseConfig";
import Product from "../../components/common/Product";
import { useRouter } from "next/router";
import MyLink from "../../components/common/MyLink";

export default function Products() {
  const databaseRef = collection(database, "data");
  const [fireData, setFireData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const router = useRouter();

  console.log(router.query);
  useEffect(() => {
    getData();
  }, []);

  useEffect(() => {
    if (router.query.p) setCurrentPage(router.query.p);
  }, [router]);

  const getData = async () => {
    await getDocs(databaseRef).then((response) => {
      setFireData(
        response.docs.map((data) => {
          return { ...data.data(), id: data.id };
        })
      );
    });
  };
  console.log(currentPage);
  return (
    <MainLayout>
      <Title title={"SẢN PHẨM"} />
      <section className="mt-10 mb-5 text-white md:mt-16">
        <div className="grid grid-cols-1 gap-0 md:grid-cols-4 md:gap-2">
          {[1, 2, 3, 4, 5, 6, 7, 8].map((item, index) => (
            <Product key={index} product={{ name: "Cửa đi" }} />
          ))}
        </div>
      </section>

      <div className=" mx-auto mt-10 w-full text-center">
        <nav aria-label="Page navigation example">
          <ul className="inline-flex items-center -space-x-px">
            <li>
              <a href="#" className="pagination-item ml-0 block rounded-l-lg">
                <span className="sr-only">Previous</span>
                <svg
                  className="h-5 w-5"
                  fill="currentColor"
                  viewBox="0 0 20 20"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    fillRule="evenodd"
                    d="M12.707 5.293a1 1 0 010 1.414L9.414 10l3.293 3.293a1 1 0 01-1.414 1.414l-4-4a1 1 0 010-1.414l4-4a1 1 0 011.414 0z"
                    clipRule="evenodd"
                  ></path>
                </svg>
              </a>
            </li>
            <li>
              <MyLink href="?p=1">
                <a
                  className={`${
                    +currentPage === 1
                      ? "pagination-current-item"
                      : "pagination-item"
                  }`}
                >
                  1
                </a>
              </MyLink>
            </li>
            <li>
              <MyLink href="?p=2">
                <a
                  href="?p=2"
                  className={`${
                    +currentPage === 2
                      ? "pagination-current-item"
                      : "pagination-item"
                  }`}
                >
                  2
                </a>
              </MyLink>
            </li>
            <li>
              <MyLink href="?p=3">
                <a
                  href="?p=3"
                  aria-current="page"
                  className={`${
                    +currentPage === 3
                      ? "pagination-current-item"
                      : "pagination-item"
                  }`}
                >
                  3
                </a>
              </MyLink>
            </li>
            <li>
              <MyLink href="?p=4">
                <a
                  href="?p=4"
                  className={`${
                    +currentPage === 4
                      ? "pagination-current-item"
                      : "pagination-item"
                  }`}
                >
                  4
                </a>
              </MyLink>
            </li>
            <li>
              <MyLink href="?p=5">
                <a
                  href="?p=5"
                  className={`${
                    +currentPage === 5
                      ? "pagination-current-item"
                      : "pagination-item"
                  }`}
                >
                  5
                </a>
              </MyLink>
            </li>
            <li>
              <a href="#" className="pagination-item block rounded-r-lg">
                <span className="sr-only">Next</span>
                <svg
                  className="h-5 w-5"
                  fill="currentColor"
                  viewBox="0 0 20 20"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    fillRule="evenodd"
                    d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z"
                    clipRule="evenodd"
                  ></path>
                </svg>
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </MainLayout>
  );
}
