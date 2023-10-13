"use client";

import AdvertisingCard from "@/components/card-advertising";
import PostCategoryCard from "@/components/card-post-category";
import Link from "next/link";
import { useState } from "react";

export default function Home() {
  // console.log(process.env.VITE_APP_BASE_API);
  const [postList, setPostList] = useState<
    {
      id: string;
      title: string;
    }[]
  >([
    {
      id: "1",
      title: "title1",
    },
  ]);
  return (
    <>
      {/* Page header */}
      <div className="mx-auto my-10 max-w-3xl sm:px-6  md:items-center md:justify-between lg:max-w-7xl">
        <div className="bg-white p-4 shadow sm:rounded-lg">
          <h1 className="text-2xl font-bold text-gray-900">Ricardo Cooper</h1>
        </div>
      </div>

      <div className="mx-auto mt-8 grid max-w-3xl grid-cols-1 gap-6 sm:px-6 lg:max-w-7xl lg:grid-flow-col-dense lg:grid-cols-3">
        <div className="space-y-6 lg:col-span-2 lg:col-start-1">
          {/* Description list*/}
          <section aria-labelledby="applicant-information-title">
            <Link href={`/post/${postList[0].id}`}>
              <div className="bg-white px-4 py-5 shadow sm:rounded-lg sm:px-6">
                <h2
                  id="applicant-information-title"
                  className="text-lg font-medium leading-6 text-gray-900"
                >
                  Applicant Information
                </h2>
                <p className="mt-1 max-w-2xl text-sm text-gray-500">
                  Personal details and application.
                </p>
              </div>
            </Link>
          </section>
        </div>

        <PostCategoryCard className="lg:col-span-1 lg:col-start-3" />

        <AdvertisingCard className="lg:col-span-1 lg:col-start-3" />
      </div>
    </>
  );
}
