"use client"
import { Music, Tv, Cloud, Book } from "lucide-react"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"

import { Header } from "@/components/header"
import { SubscriptionCard } from "@/components/subscription-card"
import { AddSubscriptionForm } from "@/components/add-subscription-form"

export default function SubscriptionsPage() {
  // Mock data for subscriptions
  const subscriptions = [
    {
      id: 1,
      name: "Netflix",
      amount: 15.99,
      cycle: "Monthly",
      nextPayment: "2024-04-15",
      icon: <Tv className="h-4 w-4 text-red-500" />,
      color: "bg-red-100",
    },
    {
      id: 2,
      name: "Spotify",
      amount: 9.99,
      cycle: "Monthly",
      nextPayment: "2024-04-10",
      icon: <Music className="h-4 w-4 text-green-500" />,
      color: "bg-green-100",
    },
    {
      id: 3,
      name: "iCloud",
      amount: 2.99,
      cycle: "Monthly",
      nextPayment: "2024-04-05",
      icon: <Cloud className="h-4 w-4 text-blue-500" />,
      color: "bg-blue-100",
    },
    {
      id: 4,
      name: "Medium",
      amount: 5.0,
      cycle: "Monthly",
      nextPayment: "2024-04-22",
      icon: <Book className="h-4 w-4 text-yellow-500" />,
      color: "bg-yellow-100",
    },
  ]

  // Calculate total monthly cost
  const totalMonthlyCost = subscriptions.reduce((sum, sub) => sum + sub.amount, 0)

  return (
    <>
      <Header title="Subscriptions" />

      <main className="flex flex-col gap-4 p-4">
        <Card>
          <CardContent className="p-3">
            <div className="flex justify-between items-center">
              <div>
                <h3 className="font-medium">Total Monthly</h3>
                <p className="text-2xl font-bold">R{totalMonthlyCost.toFixed(2)}</p>
              </div>
              <div className="flex flex-col items-end">
                <span className="text-sm text-muted-foreground">{subscriptions.length} active</span>
                <span className="text-xs text-muted-foreground">subscriptions</span>
              </div>
            </div>
          </CardContent>
        </Card>

        <Tabs defaultValue="active" className="space-y-4">
          <TabsList className="grid grid-cols-2 h-9">
            <TabsTrigger value="active">Active</TabsTrigger>
            <TabsTrigger value="add">Add New</TabsTrigger>
          </TabsList>

          <TabsContent value="active" className="space-y-4">
            <div className="grid gap-3">
              {subscriptions.map((subscription) => (
                <SubscriptionCard
                  key={subscription.id}
                  name={subscription.name}
                  amount={subscription.amount}
                  cycle={subscription.cycle}
                  nextPayment={subscription.nextPayment}
                  icon={subscription.icon}
                  color={subscription.color}
                />
              ))}
            </div>
          </TabsContent>

          <TabsContent value="add">
            <Card>
              <CardHeader className="p-3">
                <CardTitle className="text-lg">Add Subscription</CardTitle>
                <CardDescription>Track your recurring payments</CardDescription>
              </CardHeader>
              <CardContent className="p-3 pt-0">
                <AddSubscriptionForm />
              </CardContent>
            </Card>
          </TabsContent>
        </Tabs>
      </main>
    </>
  )
}

