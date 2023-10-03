/* 作者信息卡片组件 */

export default function Component({ className }: { className?: string }) {
  return (
    <section aria-labelledby="advertising-card" className={className}>
      <div className="bg-white px-4 py-5 shadow sm:rounded-lg sm:px-6">
        <h2 id="advertising-card" className="text-lg font-medium text-gray-900">
          作者信息
        </h2>

        {/* Content */}
        <div className="mt-6 flow-root">
          <div className="mx-auto md:flex md:items-center md:justify-between md:space-x-5 lg:max-w-7xl">
            <div className="flex items-center space-x-5">
              <div className="flex-shrink-0">
                <div className="relative">
                  <img
                    className="h-16 w-16 rounded-full"
                    src="https://images.unsplash.com/photo-1463453091185-61582044d556?ixlib=rb-=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=8&w=1024&h=1024&q=80"
                    alt=""
                  />
                  <span
                    className="absolute inset-0 rounded-full shadow-inner"
                    aria-hidden="true"
                  />
                </div>
              </div>
              <div>
                <h1 className="text-2xl font-bold text-gray-900">
                  Ricardo Cooper
                </h1>
                <p className="text-sm font-medium text-gray-500">
                  Applied for{" "}
                  <a href="#" className="text-gray-900">
                    Front End Developer
                  </a>{" "}
                  on <time dateTime="2020-08-25">August 25, 2020</time>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
