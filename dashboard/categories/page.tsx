"use client"

import { Plus } from "lucide-react"

import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"

import { Header } from "@/components/header"
import { CategoryList } from "@/components/category-list"
import { AddCategoryForm } from "@/components/add-category-form"

export default function CategoriesPage() {
  return (
    <>
      <Header title="Categories" />

      <main className="flex flex-col gap-4 p-4">
        <Tabs defaultValue="list" className="space-y-4">
          <TabsList className="grid grid-cols-2 h-9">
            <TabsTrigger value="list">All Categories</TabsTrigger>
            <TabsTrigger value="add">Add New</TabsTrigger>
          </TabsList>

          <TabsContent value="list" className="space-y-4">
            <Card>
              <CardHeader className="p-3">
                <div className="flex justify-between items-center">
                  <CardTitle className="text-lg">Categories</CardTitle>
                  <Button size="sm" variant="outline">
                    <Plus className="h-4 w-4 mr-1" />
                    Add
                  </Button>
                </div>
                <CardDescription>Manage your expense categories</CardDescription>
              </CardHeader>
              <CardContent className="p-3 pt-0">
                <CategoryList />
              </CardContent>
            </Card>
          </TabsContent>

          <TabsContent value="add">
            <Card>
              <CardHeader className="p-3">
                <CardTitle className="text-lg">Add Category</CardTitle>
                <CardDescription>Create a new expense category</CardDescription>
              </CardHeader>
              <CardContent className="p-3 pt-0">
                <AddCategoryForm />
              </CardContent>
            </Card>
          </TabsContent>
        </Tabs>
      </main>
    </>
  )
}

