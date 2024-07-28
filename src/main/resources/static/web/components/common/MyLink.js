import React from 'react';
import Link from 'next/link';
import { useRouter } from 'next/router';

const MyLink = ({ href, children }) => {
  const router = useRouter();
  let className = children?.props?.className || '';
  if (
    router?.pathname === href ||
    `/${router?.pathname?.split('/')[1]}` === href
  ) {
    className = `${className} text-[#FACC15]`;
  }

  return <Link href={href}>{React.cloneElement(children, { className })}</Link>;
};

export default MyLink;
