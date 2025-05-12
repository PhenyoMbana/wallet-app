import Link from "next/link"
import { ArrowRight } from "lucide-react"

import { Button } from "@/components/ui/button"

export default function Home() {
  return (
    <div className="flex min-h-screen flex-col">
      <header className="px-4 h-14 flex items-center border-b">
        <span className="font-bold text-xl">Digital Wallet</span>
        <nav className="ml-auto flex gap-4">
          <Link className="text-sm font-medium hover:underline underline-offset-4" href="/login">
            Login
          </Link>
          <Link className="text-sm font-medium hover:underline underline-offset-4" href="/register">
            Register
          </Link>
        </nav>
      </header>
      <main className="flex-1">
        <section className="w-full py-12">
          <div className="container px-4">
            <div className="flex flex-col items-center space-y-4 text-center">
              <div className="space-y-2">
                <h1 className="text-3xl font-bold tracking-tighter">Manage Your Finances with Ease</h1>
                <p className="text-gray-500 dark:text-gray-400">
                  Track expenses, set budgets, and visualize your spending habits all in one place.
                </p>
              </div>
              <div className="space-x-4">
                <Link href="/register">
                  <Button className="inline-flex h-10 items-center justify-center rounded-md bg-primary px-8">
                    Get Started
                    <ArrowRight className="ml-2 h-4 w-4" />
                  </Button>
                </Link>
              </div>
            </div>
          </div>
        </section>
        <section className="w-full py-12 bg-muted">
          <div className="container px-4">
            <div className="grid gap-6 items-start">
              <div className="flex flex-col justify-center space-y-4">
                <div className="space-y-2">
                  <h2 className="text-2xl font-bold tracking-tighter">Track Expenses</h2>
                  <p className="text-gray-500 dark:text-gray-400">
                    Easily add and categorize your expenses to keep track of your spending.
                  </p>
                </div>
              </div>
              <div className="flex flex-col justify-center space-y-4">
                <div className="space-y-2">
                  <h2 className="text-2xl font-bold tracking-tighter">Set Budgets</h2>
                  <p className="text-gray-500 dark:text-gray-400">
                    Create monthly budgets for different categories and monitor your progress.
                  </p>
                </div>
              </div>
              <div className="flex flex-col justify-center space-y-4">
                <div className="space-y-2">
                  <h2 className="text-2xl font-bold tracking-tighter">Visualize Data</h2>
                  <p className="text-gray-500 dark:text-gray-400">
                    View graphs and charts to understand your spending habits better.
                  </p>
                </div>
              </div>
            </div>
          </div>
        </section>
      </main>
      <footer className="flex flex-col gap-2 py-6 w-full shrink-0 items-center px-4 border-t">
        <p className="text-xs text-gray-500 dark:text-gray-400">© 2024 Digital Wallet. All rights reserved.</p>
      </footer>
    </div>
  )
}

