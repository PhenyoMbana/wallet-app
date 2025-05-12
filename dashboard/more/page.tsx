"use client"

import Link from "next/link"
import { BarChart3, CreditCard, Globe, Package, Settings } from "lucide-react"
import { Card, CardContent } from "@/components/ui/card"

import { Header } from "@/components/header"

export default function MorePage() {
  const menuItems = [
    {
      title: "Categories",
      icon: <Package className="h-5 w-5" />,
      href: "/dashboard/categories",
      color: "bg-blue-100 text-blue-600",
    },
    {
      title: "Reports",
      icon: <BarChart3 className="h-5 w-5" />,
      href: "/dashboard/reports",
      color: "bg-purple-100 text-purple-600",
    },
    {
      title: "Subscriptions",
      icon: <CreditCard className="h-5 w-5" />,
      href: "/dashboard/subscriptions",
      color: "bg-pink-100 text-pink-600",
    },
    {
      title: "Debt Tracker",
      icon: <CreditCard className="h-5 w-5" />,
      href: "/dashboard/debt",
      color: "bg-red-100 text-red-600",
    },
    {
      title: "Currencies",
      icon: <Globe className="h-5 w-5" />,
      href: "/dashboard/currencies",
      color: "bg-green-100 text-green-600",
    },
    {
      title: "Settings",
      icon: <Settings className="h-5 w-5" />,
      href: "/dashboard/settings",
      color: "bg-gray-100 text-gray-600",
    },
  ]

  return (
    <>
      <Header title="More Options" />

      <main className="flex flex-col gap-4 p-4">
        <div className="grid grid-cols-2 gap-3">
          {menuItems.map((item) => (
            <Link key={item.href} href={item.href}>
              <Card className="hover:bg-muted/50 transition-colors">
                <CardContent className="p-4 flex flex-col items-center text-center">
                  <div className={`h-10 w-10 rounded-full ${item.color} flex items-center justify-center mb-2`}>
                    {item.icon}
                  </div>
                  <span className="text-sm font-medium">{item.title}</span>
                </CardContent>
              </Card>
            </Link>
          ))}
        </div>
      </main>
    </>
  )
}

