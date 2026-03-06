import type { AnchorHTMLAttributes, ButtonHTMLAttributes, ReactNode } from "react";

interface SharedProps {
  children: ReactNode;
  variant?: "primary" | "secondary" | "ghost";
  className?: string;
}

type Props =
  | ({ href: string } & AnchorHTMLAttributes<HTMLAnchorElement> & SharedProps)
  | ({ href?: undefined } & ButtonHTMLAttributes<HTMLButtonElement> & SharedProps);

const variants = {
  primary:
    "border border-cyan-400/30 bg-cyan-400/18 text-cyan-100 hover:border-cyan-300 hover:bg-cyan-400/28",
  secondary:
    "border border-white/14 bg-white/[0.04] text-slate-100 hover:border-white/28 hover:bg-white/[0.08]",
  ghost:
    "border border-transparent bg-transparent text-slate-200 hover:border-white/14 hover:bg-white/[0.05]",
};

export function Button({ children, className = "", variant = "primary", ...props }: Props) {
  const classes = `inline-flex items-center justify-center rounded-full px-5 py-2.5 text-sm font-semibold transition focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-cyan-300 ${variants[variant]} ${className}`.trim();

  if ("href" in props && props.href) {
    return (
      <a {...props} className={classes}>
        {children}
      </a>
    );
  }

  return (
    <button {...props} className={classes}>
      {children}
    </button>
  );
}
