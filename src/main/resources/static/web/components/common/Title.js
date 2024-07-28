import React from 'react';
export default function Title({ title }) {
  return (
    <section className="mt-10 mb-5 flex text-white md:mt-16">
      <div className="flex w-full rounded bg-[#0060af] p-2">
        <small className=" ml-2 text-sm  font-semibold uppercase md:text-base">
          {title}
        </small>
      </div>
    </section>
  );
}
