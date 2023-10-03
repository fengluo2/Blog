"use client";
import { useRouter, useSearchParams } from "next/navigation";
import { useState } from "react";
import PostCategoryCard from "@/components/card-post-category";
import AdvertisingCard from "@/components/card-advertising";
import { Disclosure } from "@headlessui/react";
import { ChevronUpIcon } from "@heroicons/react/20/solid";

export default function Search() {
  const searchParams = useSearchParams();
  const [searchOption, setSearchOption] = useState({
    keyword: searchParams.get("keyword")?.toString().trim() ?? "",
    range: searchParams.get("range")?.toString().trim() ?? "",
    sort: searchParams.get("sort")?.toString().trim() ?? "",
  });
  const [extendConditionsRange, setExtendConditionsRange] = useState(false);
  const [extendConditionsSort, setExtendConditionsSort] = useState(false);

  if (searchOption.keyword){
    searchSub();
  }

  function searchSub() {
    //TODO 搜索方法
    console.log(searchOption);
  }

  function openExtendConditions(): boolean {
    return extendConditionsRange || extendConditionsSort;
  }

  function ExtendSearchConditionsRangeDiv() {
    if (extendConditionsRange)
      return (
        <div className="flex border-b px-2">
          <div className="grow space-x-2">
            <button>一天内</button>
            <button>一周内</button>
            <button>一年内</button>
          </div>
          <span className="flex-none">范围</span>
        </div>
      );
    else return null;
  }

  function ExtendSearchConditionsSortDiv() {
    if (extendConditionsSort)
      return (
        <div className="flex border-b px-2">
          <div className="grow space-x-2">
            <button>综合</button>
            <button>最新</button>
            <button>热门</button>
          </div>
          <span className="flex-none">排序</span>
        </div>
      );
    else return null;
  }

  return (
    <main className="py-10">
      <div className="mx-auto max-w-3xl space-y-8 sm:px-6 md:items-center md:justify-between lg:max-w-7xl">
        <div className="bg-white px-0 shadow sm:rounded-lg">
          <div
            className={
              "flex border-2 border-gray-300 shadow " +
              (openExtendConditions() ? "sm:rounded-t-lg" : "sm:rounded-lg")
            }
          >
            <input
              value={searchOption.keyword}
              className="w-full border-none focus:ring-0 sm:rounded-lg"
              onChange={(e) => {
                setSearchOption({
                  keyword: e.target.value,
                  range: searchOption.range,
                  sort: searchOption.sort,
                });
              }}
              onKeyDown={(e) => {
                if (e.code == "Enter" || e.code == "NumpadEnter") searchSub();
              }}
            />
            <button
              className="whitespace-nowrap border-r border-r-gray-400 bg-gray-300 px-2 hover:bg-gray-400"
              onClick={() => {
                setExtendConditionsRange(!extendConditionsRange);
              }}
            >
              范围
            </button>
            <button
              className={
                "whitespace-nowrap bg-gray-300 px-2 hover:bg-gray-400 " +
                (openExtendConditions()
                  ? "sm:rounded-tr-[5px]"
                  : "sm:rounded-r-[5px]")
              }
              onClick={() => {
                setExtendConditionsSort(!extendConditionsSort);
              }}
            >
              排序
            </button>
          </div>
          <ExtendSearchConditionsRangeDiv />
          <ExtendSearchConditionsSortDiv />
        </div>

        <div className="grid grid-cols-1 gap-6 lg:grid-flow-col-dense lg:grid-cols-3">
          <section className="space-y-4 lg:col-span-2 lg:col-start-1">
            <div className="bg-white px-4 py-5 shadow sm:rounded-lg sm:px-6">
              文章1
            </div>
            <div className="bg-white px-4 py-5 shadow sm:rounded-lg sm:px-6">
              文章2
            </div>
          </section>

          <div className="space-y-6 lg:col-span-1 lg:col-start-3">
            <PostCategoryCard />

            <AdvertisingCard />
          </div>
        </div>
      </div>
    </main>
  );
}
