"use client"

import { Plus } from "lucide-react"

import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"

import { Header } from "@/components/header"
import { ExpenseList } from "@/components/expense-list"
import { AddExpenseForm } from "@/components/add-expense-form"

export default function ExpensesPage() {
  return (
    <>
      <Header title="Expenses" />

      <main className="flex flex-col gap-4 p-4">
        <Tabs defaultValue="list" className="space-y-4">
          <TabsList className="grid grid-cols-2 h-9">
            <TabsTrigger value="list">All Expenses</TabsTrigger>
            <TabsTrigger value="add">Add New</TabsTrigger>
          </TabsList>

          <TabsContent value="list" className="space-y-4">
            <Card>
              <CardHeader className="p-3">
                <div className="flex justify-between items-center">
                  <CardTitle className="text-lg">All Expenses</CardTitle>
                  <Button size="sm" variant="outline">
                    <Plus className="h-4 w-4 mr-1" />
                    Add
                  </Button>
                </div>
                <CardDescription>Manage and view all your expenses</CardDescription>
              </CardHeader>
              <CardContent className="p-3 pt-0">
                <ExpenseList />
              </CardContent>
            </Card>
          </TabsContent>

          <TabsContent value="add">
            <Card>
              <CardHeader className="p-3">
                <CardTitle className="text-lg">Add New Expense</CardTitle>
                <CardDescription>Record a new expense</CardDescription>
              </CardHeader>
              <CardContent className="p-3 pt-0">
                <AddExpenseForm />
              </CardContent>
            </Card>
          </TabsContent>
        </Tabs>
      </main>
    </>
  )
}

