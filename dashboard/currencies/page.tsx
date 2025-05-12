"use client"

import { useState } from "react"
import { ArrowDownUp, DollarSign, Euro, Globe, PoundSterling, JapaneseYenIcon as Yen } from "lucide-react"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"

import { Header } from "@/components/header"
import { CurrencyConverter } from "@/components/currency-converter"

// Custom ZAR icon component
function ZarIcon({ className }: { className?: string }) {
  return <div className={`flex items-center justify-center font-bold ${className}`}>R</div>
}

export default function CurrenciesPage() {
  // Mock data for currencies
  const currencies = [
    { code: "ZAR", name: "South African Rand", symbol: "R", icon: <ZarIcon className="h-4 w-4" /> },
    { code: "USD", name: "US Dollar", symbol: "$", icon: <DollarSign className="h-4 w-4" /> },
    { code: "EUR", name: "Euro", symbol: "€", icon: <Euro className="h-4 w-4" /> },
    { code: "GBP", name: "British Pound", symbol: "£", icon: <PoundSterling className="h-4 w-4" /> },
    { code: "JPY", name: "Japanese Yen", symbol: "¥", icon: <Yen className="h-4 w-4" /> },
  ]

  const [baseCurrency, setBaseCurrency] = useState("ZAR")

  return (
    <>
      <Header title="Currencies" />

      <main className="flex flex-col gap-4 p-4">
        <Card className="border-blue-violet">
          <CardContent className="p-3">
            <div className="flex items-center justify-between">
              <div>
                <h3 className="font-medium">Base Currency</h3>
                <p className="text-sm text-muted-foreground">All expenses will be converted to this currency</p>
              </div>
              <Select value={baseCurrency} onValueChange={setBaseCurrency}>
                <SelectTrigger className="w-[110px] bg-blue-violet text-white">
                  <SelectValue placeholder="Select currency" />
                </SelectTrigger>
                <SelectContent>
                  {currencies.map((currency) => (
                    <SelectItem key={currency.code} value={currency.code}>
                      <div className="flex items-center">
                        {currency.icon}
                        <span className="ml-2">{currency.code}</span>
                      </div>
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
          </CardContent>
        </Card>

        <Tabs defaultValue="converter" className="space-y-4">
          <TabsList className="grid grid-cols-2 h-9 bg-sun-glare text-darkest-hour">
            <TabsTrigger
              value="converter"
              className="data-[state=active]:bg-exuberant-orange data-[state=active]:text-white"
            >
              Converter
            </TabsTrigger>
            <TabsTrigger
              value="rates"
              className="data-[state=active]:bg-exuberant-orange data-[state=active]:text-white"
            >
              Exchange Rates
            </TabsTrigger>
          </TabsList>

          <TabsContent value="converter">
            <Card className="border-exuberant-orange">
              <CardHeader className="p-3">
                <div className="flex items-center">
                  <CardTitle className="text-lg text-blue-violet">Currency Converter</CardTitle>
                  <ArrowDownUp className="h-4 w-4 ml-2 text-exuberant-orange" />
                </div>
                <CardDescription>Convert between different currencies</CardDescription>
              </CardHeader>
              <CardContent className="p-3 pt-0">
                <CurrencyConverter currencies={currencies} />
              </CardContent>
            </Card>
          </TabsContent>

          <TabsContent value="rates" className="space-y-4">
            <Card className="border-exuberant-orange">
              <CardHeader className="p-3">
                <div className="flex items-center">
                  <CardTitle className="text-lg text-blue-violet">Exchange Rates</CardTitle>
                  <Globe className="h-4 w-4 ml-2 text-exuberant-orange" />
                </div>
                <CardDescription>Current rates against {baseCurrency}</CardDescription>
              </CardHeader>
              <CardContent className="p-3 pt-0">
                <div className="space-y-3">
                  {currencies
                    .filter((c) => c.code !== baseCurrency)
                    .map((currency) => {
                      // Mock exchange rates for ZAR
                      const rates = {
                        ZAR: { USD: 0.054, EUR: 0.05, GBP: 0.043, JPY: 8.18 },
                        USD: { ZAR: 18.52, EUR: 0.92, GBP: 0.79, JPY: 151.45 },
                        EUR: { ZAR: 20.13, USD: 1.09, GBP: 0.86, JPY: 164.62 },
                        GBP: { ZAR: 23.41, USD: 1.27, EUR: 1.16, JPY: 191.42 },
                        JPY: { ZAR: 0.122, USD: 0.0066, EUR: 0.0061, GBP: 0.0052 },
                      }

                      const rate =
                        rates[baseCurrency as keyof typeof rates][
                          currency.code as keyof (typeof rates)[typeof baseCurrency]
                        ]

                      return (
                        <div
                          key={currency.code}
                          className="flex items-center justify-between p-2 rounded-md border border-blue-violet/20 hover:bg-cloud-dancer transition-colors"
                        >
                          <div className="flex items-center">
                            <div className="h-8 w-8 rounded-full bg-blue-violet/10 flex items-center justify-center text-blue-violet">
                              {currency.icon}
                            </div>
                            <div className="ml-3">
                              <p className="font-medium">{currency.code}</p>
                              <p className="text-xs text-muted-foreground">{currency.name}</p>
                            </div>
                          </div>
                          <div className="text-right">
                            <p className="font-medium text-blue-violet">{rate.toFixed(4)}</p>
                            <p className="text-xs text-muted-foreground">1 {baseCurrency} =</p>
                          </div>
                        </div>
                      )
                    })}
                </div>
              </CardContent>
            </Card>
          </TabsContent>
        </Tabs>
      </main>
    </>
  )
}

