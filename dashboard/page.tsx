"use client"

import Link from "next/link"
import { CreditCard, DollarSign, PieChart, Plus } from "lucide-react"

import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Progress } from "@/components/ui/progress"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"

import { Header } from "@/components/header"
import { ExpenseChart } from "@/components/expense-chart"
import { ExpenseList } from "@/components/expense-list"

export default function DashboardPage() {
  // Mock data for the dashboard
  const totalBudget = 2000
  const totalSpent = 1250
  const percentSpent = (totalSpent / totalBudget) * 100

  return (
    <>
      <Header title="Dashboard" />

      <main className="flex flex-col gap-4 p-4">
        {/* Budget Overview */}
        <div className="grid grid-cols-2 gap-3">
          <Card>
            <CardHeader className="flex flex-row items-center justify-between space-y-0 p-3">
              <CardTitle className="text-sm font-medium">Budget</CardTitle>
              <DollarSign className="h-4 w-4 text-muted-foreground" />
            </CardHeader>
            <CardContent className="p-3 pt-0">
              <div className="text-xl font-bold">R{totalBudget}</div>
              <p className="text-xs text-muted-foreground">Monthly</p>
            </CardContent>
          </Card>
          <Card>
            <CardHeader className="flex flex-row items-center justify-between space-y-0 p-3">
              <CardTitle className="text-sm font-medium">Spent</CardTitle>
              <CreditCard className="h-4 w-4 text-muted-foreground" />
            </CardHeader>
            <CardContent className="p-3 pt-0">
              <div className="text-xl font-bold">R{totalSpent}</div>
              <p className="text-xs text-muted-foreground">This month</p>
            </CardContent>
          </Card>
        </div>

        <Card>
          <CardHeader className="flex flex-row items-center justify-between space-y-0 p-3">
            <CardTitle className="text-sm font-medium">Budget Progress</CardTitle>
            <PieChart className="h-4 w-4 text-muted-foreground" />
          </CardHeader>
          <CardContent className="p-3 pt-0">
            <div className="flex justify-between mb-1">
              <div className="text-sm font-medium">{percentSpent.toFixed(0)}% spent</div>
              <div className="text-sm font-medium">
                R{totalSpent} / R{totalBudget}
              </div>
            </div>
            <Progress value={percentSpent} className="h-2" />
            <p className="mt-2 text-xs text-muted-foreground">R{(totalBudget - totalSpent).toFixed(2)} remaining</p>
          </CardContent>
        </Card>

        <div className="flex justify-between">
          <Link href="/dashboard/expenses">
            <Button size="sm">
              <Plus className="mr-2 h-4 w-4" />
              Add Expense
            </Button>
          </Link>
          <Link href="/dashboard/budget">
            <Button variant="outline" size="sm">
              <DollarSign className="mr-2 h-4 w-4" />
              Set Budget
            </Button>
          </Link>
        </div>

        {/* Tabs for different sections */}
        <Tabs defaultValue="expenses" className="space-y-4">
          <TabsList className="grid grid-cols-2 h-9">
            <TabsTrigger value="expenses">Recent Expenses</TabsTrigger>
            <TabsTrigger value="breakdown">Breakdown</TabsTrigger>
          </TabsList>

          <TabsContent value="expenses" className="space-y-4">
            <Card>
              <CardHeader className="p-3">
                <CardTitle className="text-lg">Recent Expenses</CardTitle>
                <CardDescription>Your latest transactions</CardDescription>
              </CardHeader>
              <CardContent className="p-3 pt-0">
                <ExpenseList limit={5} />
              </CardContent>
            </Card>
          </TabsContent>

          <TabsContent value="breakdown" className="space-y-4">
            <Card>
              <CardHeader className="p-3">
                <CardTitle className="text-lg">Expense Breakdown</CardTitle>
                <CardDescription>Your spending by category</CardDescription>
              </CardHeader>
              <CardContent className="p-0 pt-0">
                <ExpenseChart />
              </CardContent>
            </Card>
          </TabsContent>
        </Tabs>
      </main>
    </>
  )
}

