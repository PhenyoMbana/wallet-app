"use client"
import { CreditCard } from "lucide-react"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Progress } from "@/components/ui/progress"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"

import { Header } from "@/components/header"
import { DebtCard } from "@/components/debt-card"
import { AddDebtForm } from "@/components/add-debt-form"

export default function DebtPage() {
  // Mock data for debts
  const debts = [
    {
      id: 1,
      name: "Student Loan",
      totalAmount: 15000,
      remainingAmount: 8500,
      interestRate: 4.5,
      minimumPayment: 150,
      dueDate: "2024-04-15",
    },
    {
      id: 2,
      name: "Credit Card",
      totalAmount: 3000,
      remainingAmount: 1200,
      interestRate: 18.99,
      minimumPayment: 50,
      dueDate: "2024-04-05",
    },
    {
      id: 3,
      name: "Car Loan",
      totalAmount: 20000,
      remainingAmount: 12000,
      interestRate: 3.9,
      minimumPayment: 350,
      dueDate: "2024-04-20",
    },
  ]

  // Calculate total debt
  const totalDebt = debts.reduce((sum, debt) => sum + debt.remainingAmount, 0)
  const totalOriginalDebt = debts.reduce((sum, debt) => sum + debt.totalAmount, 0)
  const totalProgress = ((totalOriginalDebt - totalDebt) / totalOriginalDebt) * 100

  // Calculate total monthly payment
  const totalMonthlyPayment = debts.reduce((sum, debt) => sum + debt.minimumPayment, 0)

  return (
    <>
      <Header title="Debt Tracker" />

      <main className="flex flex-col gap-4 p-4">
        <div className="grid grid-cols-2 gap-3">
          <Card>
            <CardContent className="p-3">
              <div className="flex flex-col">
                <span className="text-sm text-muted-foreground">Total Debt</span>
                <span className="text-2xl font-bold">R{totalDebt.toLocaleString()}</span>
                <Progress value={totalProgress} className="h-1.5 mt-2" />
                <span className="text-xs text-muted-foreground mt-1">{totalProgress.toFixed(0)}% paid off</span>
              </div>
            </CardContent>
          </Card>
          <Card>
            <CardContent className="p-3">
              <div className="flex flex-col">
                <span className="text-sm text-muted-foreground">Monthly Payment</span>
                <span className="text-2xl font-bold">R{totalMonthlyPayment.toLocaleString()}</span>
                <div className="flex items-center mt-2 text-xs text-muted-foreground">
                  <CreditCard className="h-3.5 w-3.5 mr-1" />
                  <span>Next payment due soon</span>
                </div>
              </div>
            </CardContent>
          </Card>
        </div>

        <Tabs defaultValue="overview" className="space-y-4">
          <TabsList className="grid grid-cols-2 h-9">
            <TabsTrigger value="overview">All Debts</TabsTrigger>
            <TabsTrigger value="add">Add New</TabsTrigger>
          </TabsList>

          <TabsContent value="overview" className="space-y-4">
            <div className="grid gap-3">
              {debts.map((debt) => (
                <DebtCard
                  key={debt.id}
                  name={debt.name}
                  totalAmount={debt.totalAmount}
                  remainingAmount={debt.remainingAmount}
                  interestRate={debt.interestRate}
                  minimumPayment={debt.minimumPayment}
                  dueDate={debt.dueDate}
                />
              ))}
            </div>
          </TabsContent>

          <TabsContent value="add">
            <Card>
              <CardHeader className="p-3">
                <CardTitle className="text-lg">Add Debt</CardTitle>
                <CardDescription>Track your loans and repayments</CardDescription>
              </CardHeader>
              <CardContent className="p-3 pt-0">
                <AddDebtForm />
              </CardContent>
            </Card>
          </TabsContent>
        </Tabs>
      </main>
    </>
  )
}

