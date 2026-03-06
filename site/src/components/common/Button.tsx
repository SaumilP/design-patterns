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
    "border border-cyan-400/30 bg-[var(--button-primary-bg)] text-[var(--button-primary-text)] hover:border-cyan-300 hover:bg-[var(--button-primary-bg-hover)]",
  secondary:
    "border border-[color:var(--border-strong)] bg-[var(--panel-soft)] text-[var(--text-primary)] hover:bg-[var(--panel)]",
  ghost:
    "border border-transparent bg-transparent text-[var(--text-secondary)] hover:border-[color:var(--border)] hover:bg-[var(--panel-soft)]",
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
