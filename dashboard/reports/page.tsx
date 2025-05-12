"use client"

import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"

import { Header } from "@/components/header"
import { ExpenseChart } from "@/components/expense-chart"
import { MonthlyExpenseChart } from "@/components/monthly-expense-chart"
import { CategoryPieChart } from "@/components/category-pie-chart"

export default function ReportsPage() {
  return (
    <>
      <Header title="Reports" />

      <main className="flex flex-col gap-4 p-4">
        <Tabs defaultValue="category" className="space-y-4">
          <TabsList className="grid grid-cols-3 h-9">
            <TabsTrigger value="category">Categories</TabsTrigger>
            <TabsTrigger value="monthly">Monthly</TabsTrigger>
            <TabsTrigger value="pie">Distribution</TabsTrigger>
          </TabsList>

          <TabsContent value="category" className="space-y-4">
            <Card>
              <CardHeader className="p-3">
                <CardTitle className="text-lg">Expense Breakdown</CardTitle>
                <CardDescription>Your spending by category this month</CardDescription>
              </CardHeader>
              <CardContent className="p-0">
                <ExpenseChart />
              </CardContent>
            </Card>
          </TabsContent>

          <TabsContent value="monthly" className="space-y-4">
            <Card>
              <CardHeader className="p-3">
                <CardTitle className="text-lg">Monthly Trends</CardTitle>
                <CardDescription>Your spending over the last 6 months</CardDescription>
              </CardHeader>
              <CardContent className="p-0">
                <MonthlyExpenseChart />
              </CardContent>
            </Card>
          </TabsContent>

          <TabsContent value="pie" className="space-y-4">
            <Card>
              <CardHeader className="p-3">
                <CardTitle className="text-lg">Expense Distribution</CardTitle>
                <CardDescription>Percentage breakdown of your expenses</CardDescription>
              </CardHeader>
              <CardContent className="flex justify-center py-4">
                <CategoryPieChart />
              </CardContent>
            </Card>
          </TabsContent>
        </Tabs>
      </main>
    </>
  )
}

