"use client"
import { Award, Gift, Target, Trophy, Users, PieChart, CreditCard } from "lucide-react"

import { Button } from "@/components/ui/button"
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"

import { Header } from "@/components/header"
import { ChallengeCard } from "@/components/challenge-card"
import { BadgeCard } from "@/components/badge-card"
import { LeaderboardCard } from "@/components/leaderboard-card"

export default function RewardsPage() {
  return (
    <>
      <Header title="Rewards & Challenges" />

      <main className="flex flex-col gap-4 p-4">
        <div className="flex items-center justify-between">
          <div>
            <h2 className="text-lg font-semibold text-blue-violet">Your Progress</h2>
            <p className="text-sm text-muted-foreground">Keep going to earn more rewards!</p>
          </div>
          <div className="flex items-center gap-1">
            <Trophy className="h-5 w-5 text-sun-glare" />
            <span className="font-bold text-exuberant-orange">450 pts</span>
          </div>
        </div>

        <Tabs defaultValue="challenges" className="space-y-4">
          <TabsList className="grid grid-cols-3 h-9 bg-sun-glare text-darkest-hour">
            <TabsTrigger
              value="challenges"
              className="data-[state=active]:bg-exuberant-orange data-[state=active]:text-white"
            >
              Challenges
            </TabsTrigger>
            <TabsTrigger
              value="badges"
              className="data-[state=active]:bg-exuberant-orange data-[state=active]:text-white"
            >
              Badges
            </TabsTrigger>
            <TabsTrigger
              value="leaderboard"
              className="data-[state=active]:bg-exuberant-orange data-[state=active]:text-white"
            >
              Leaderboard
            </TabsTrigger>
          </TabsList>

          <TabsContent value="challenges" className="space-y-4">
            <div className="grid gap-3">
              <ChallengeCard
                title="No-Spend Weekend"
                description="Don't spend any money this weekend"
                icon={<Target className="h-5 w-5 text-darkest-hour" />}
                reward={50}
                progress={25}
                daysLeft={5}
              />

              <ChallengeCard
                title="Save R100"
                description="Save R100 this month"
                icon={<Gift className="h-5 w-5 text-darkest-hour" />}
                reward={100}
                progress={65}
                daysLeft={12}
              />

              <ChallengeCard
                title="Budget Master"
                description="Stay under budget for 3 categories"
                icon={<Award className="h-5 w-5 text-darkest-hour" />}
                reward={75}
                progress={33}
                daysLeft={18}
              />

              <Button className="w-full mt-2 bg-blue-violet hover:bg-blue-violet/90 text-white">
                Browse More Challenges
              </Button>
            </div>
          </TabsContent>

          <TabsContent value="badges" className="space-y-4">
            <div className="grid grid-cols-2 gap-3">
              <BadgeCard
                title="Budget Pro"
                description="Set up your first budget"
                icon={<PieChart className="h-8 w-8 text-darkest-hour" />}
                earned={true}
                date="Mar 15, 2024"
              />

              <BadgeCard
                title="Saver"
                description="Save R500 total"
                icon={<Award className="h-8 w-8 text-darkest-hour" />}
                earned={true}
                date="Mar 10, 2024"
              />

              <BadgeCard
                title="Tracker"
                description="Log expenses for 30 days"
                icon={<CreditCard className="h-8 w-8 text-darkest-hour" />}
                earned={false}
                progress={80}
              />

              <BadgeCard
                title="Debt Crusher"
                description="Pay off a debt"
                icon={<Trophy className="h-8 w-8 text-darkest-hour" />}
                earned={false}
                progress={25}
              />
            </div>
          </TabsContent>

          <TabsContent value="leaderboard" className="space-y-4">
            <Card className="border-blue-violet/20">
              <CardHeader className="p-3">
                <div className="flex justify-between items-center">
                  <CardTitle className="text-lg text-blue-violet">This Month's Leaders</CardTitle>
                  <Users className="h-5 w-5 text-exuberant-orange" />
                </div>
                <CardDescription>Compete with friends on savings goals</CardDescription>
              </CardHeader>
              <CardContent className="p-3 pt-0">
                <LeaderboardCard />
              </CardContent>
              <CardFooter className="p-3 pt-0 flex justify-center">
                <Button
                  variant="outline"
                  size="sm"
                  className="border-blue-violet text-blue-violet hover:bg-blue-violet/10"
                >
                  Invite Friends
                </Button>
              </CardFooter>
            </Card>
          </TabsContent>
        </Tabs>
      </main>
    </>
  )
}

