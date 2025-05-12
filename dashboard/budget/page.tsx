"use client"

import { DollarSign } from "lucide-react"

import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Progress } from "@/components/ui/progress"

import { Header } from "@/components/header"
import { SetBudgetForm } from "@/components/set-budget-form"

export default function BudgetPage() {
  // Mock data for the budget overview
  const totalBudget = 3000
  const totalSpent = 1250
  const percentSpent = (totalSpent / totalBudget) * 100

  // Mock data for category budgets
  const categoryBudgets = [
    { name: "Groceries", budget: 400, spent: 350, percent: 87.5 },
    { name: "Housing", budget: 1500, spent: 500, percent: 33.3 },
    { name: "Transportation", budget: 200, spent: 100, percent: 50 },
    { name: "Entertainment", budget: 150, spent: 80, percent: 53.3 },
    { name: "Food", budget: 300, spent: 120, percent: 40 },
    { name: "Utilities", budget: 250, spent: 100, percent: 40 },
  ]

  return (
    <>
      <Header title="Budget" />

      <main className="flex flex-col gap-4 p-4">
        <Card>
          <CardHeader className="p-3">
            <CardTitle className="text-lg">Budget Overview</CardTitle>
            <CardDescription>Your monthly budget progress</CardDescription>
          </CardHeader>
          <CardContent className="p-3 pt-0">
            <div className="space-y-4">
              <div className="space-y-2">
                <div className="flex items-center justify-between">
                  <div className="text-sm font-medium">Total Budget</div>
                  <div className="text-sm font-medium">
                    R{totalSpent.toFixed(2)} / R{totalBudget.toFixed(2)}
                  </div>
                </div>
                <Progress value={percentSpent} className="h-2" />
                <div className="text-xs text-muted-foreground text-right">{percentSpent.toFixed(0)}% spent</div>
              </div>

              <div className="space-y-4">
                <h3 className="text-sm font-medium">Category Breakdown</h3>
                {categoryBudgets.map((category) => (
                  <div key={category.name} className="space-y-2">
                    <div className="flex items-center justify-between">
                      <div className="text-sm font-medium">{category.name}</div>
                      <div className="text-sm font-medium">
                        R{category.spent.toFixed(2)} / R{category.budget.toFixed(2)}
                      </div>
                    </div>
                    <Progress value={category.percent} className="h-2" />
                    <div className="text-xs text-muted-foreground text-right">{category.percent.toFixed(0)}% spent</div>
                  </div>
                ))}
              </div>
            </div>
          </CardContent>
        </Card>

        <Card>
          <CardHeader className="p-3">
            <div className="flex items-center">
              <CardTitle className="text-lg">Budget Settings</CardTitle>
              <DollarSign className="h-4 w-4 ml-2 text-muted-foreground" />
            </div>
            <CardDescription>Set your monthly budget</CardDescription>
          </CardHeader>
          <CardContent className="p-3 pt-0">
            <SetBudgetForm />
          </CardContent>
        </Card>
      </main>
    </>
  )
}

