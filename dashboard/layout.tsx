import type React from "react"
import { MobileNav } from "@/components/mobile-nav"

export default function DashboardLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <div className="flex min-h-screen flex-col">
      <div className="flex-1 pb-16">{children}</div>
      <MobileNav />
    </div>
  )
}

