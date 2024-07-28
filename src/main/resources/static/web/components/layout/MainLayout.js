import Head from "next/head";
import Footer from "./Footer";
import Header from "./Header";
import MessengerCustomerChat from "react-messenger-customer-chat";
export function MainLayout({ children }) {
  return (
    <div>
      <Head>
        <title>Create Next App</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <Header />

      <main className="container mx-auto  px-4 md:px-24">{children}</main>
      <MessengerCustomerChat pageId="110681018196507" appId="383903296890932" />
      <Footer />
    </div>
  );
}
