/* 广告卡片组件 */

export default function Component({ className }: { className?: string }) {
  return (
    <section aria-labelledby="advertising-card" className={className}>
      <div className="bg-white px-4 py-5 shadow sm:rounded-lg sm:px-6">
        <h2 id="advertising-card" className="text-lg font-medium text-gray-900">
          广告
        </h2>

        {/* Content */}
        <div className="mt-6 flow-root">
          <span>test</span>
        </div>
      </div>
    </section>
  );
}
